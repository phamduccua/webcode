package com.project1.api.admin;

import com.project1.converter.SubmissionEntityConverter;
import com.project1.entity.ProblemEntity;
import com.project1.entity.SubmissionEntity;
import com.project1.entity.TestCaseEntity;
import com.project1.entity.enums.language;
import com.project1.model.request.SubmitRequest;
import com.project1.repository.AddOrUpdateSubRepository;
import com.project1.repository.TestCaseRepository;
import com.project1.service.SubmitService;
import com.project1.utils.CompareOuput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/uploads")
public class SubmitAPI {
    @Autowired
    private SubmissionEntityConverter submissionEntityConverter;
    @Autowired
    private AddOrUpdateSubRepository addOrUpdateSubRepository;
    @Autowired
    private SubmitService submitService;
    private final String UPLOAD_DIR = "D:/webcode/uploads/";

    @PostMapping("/file")
    public ResponseEntity<String> submit(@RequestParam("file") MultipartFile file,
                                        @RequestParam("language") String language,
                                        @RequestParam("id") Long problemId) throws IOException {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file selected for upload");
        }
        String fileName = file.getOriginalFilename();
        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (!isValidExtension(fileExtension)) {
            return ResponseEntity.badRequest().body("Invalid file type. Only .c, .cpp, .java, .py files are allowed.");
        }
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (Files.notExists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        String filePath = UPLOAD_DIR + file.getOriginalFilename();
        File destFile = new File(filePath);
        try {
            file.transferTo(destFile);
            String fileContent = readFileContent(destFile);
            destFile.delete();
            SubmissionEntity submission = submissionEntityConverter.toSubmissonEntity(fileContent, language, problemId);
            addOrUpdateSubRepository.addOrUpdateSub(submission);
            submitService.submit(submission);
            return ResponseEntity.ok("File uploaded successfully: /uploads/" + file.getOriginalFilename());
        } catch (IOException e) {
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }
    private boolean isValidExtension(String extension) {
        return extension.equalsIgnoreCase("c") ||
                extension.equalsIgnoreCase("cpp") ||
                extension.equalsIgnoreCase("java") ||
                extension.equalsIgnoreCase("py");
    }
    private String readFileContent(File file) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }
}
