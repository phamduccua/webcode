package com.project1.api.admin;

import com.project1.converter.SubmissionEntityConverter;
import com.project1.entity.SubmissionEntity;
import com.project1.repository.AddOrUpdateSubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
@RequestMapping("/uploads")
public class SubmitAPI {
    @Autowired
    private SubmissionEntityConverter submissionEntityConverter;
    @Autowired
    private AddOrUpdateSubRepository addOrUpdateSubRepository;

    @PostMapping("/file")
    public ResponseEntity<String> submit(@RequestParam("file") MultipartFile file,
                                         @RequestParam("language") String language,
                                         @RequestParam("id") Long problemId) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file selected for upload");
        }

        try {
            String fileContent = readFileContent(file);
            SubmissionEntity submission = submissionEntityConverter.toSubmissonEntity(fileContent, language, problemId);
            addOrUpdateSubRepository.addOrUpdateSub(submission);
            return ResponseEntity.ok("File uploaded and processed successfully");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }

    private String readFileContent(MultipartFile file) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}
