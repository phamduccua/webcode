package com.project1.api.admin;

import com.project1.converter.SubmissionEntityConverter;
import com.project1.entity.SubmissionEntity;
import com.project1.repository.AddOrUpdateSubRepository;
import jakarta.annotation.PostConstruct;
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
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@RestController
@RequestMapping("/uploads")
public class SubmitAPI {
    @Autowired
    private SubmissionEntityConverter submissionEntityConverter;
    @Autowired
    private AddOrUpdateSubRepository addOrUpdateSubRepository;
    @Autowired
    private RunCode runCode;
    private final BlockingQueue<SubmissionEntity> submissionQueue = new LinkedBlockingQueue<>();
    @PostConstruct
    public void startCodeRunnerWorker() {
        new Thread(new CodeRunnerWorker(submissionQueue,runCode)).start();
    }

    @PostMapping("/file")
    public ResponseEntity<String> submit(@RequestParam("file") MultipartFile file,
                                         @RequestParam("language") String language,
                                         @RequestParam("id") Long problemId) {
        String fileContent;
        String fileName;
        try {
            fileName = file.getOriginalFilename();
            fileName = fileName.substring(0, fileName.lastIndexOf('.'));
            fileContent = readFileContent(file);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
        SubmissionEntity submission = submissionEntityConverter.toSubmissonEntity(fileContent, language, problemId, fileName);
        addOrUpdateSubRepository.addOrUpdateSub(submission);

        try {
            submissionQueue.put(submission);
            return ResponseEntity.ok("Submission added to queue successfully");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return ResponseEntity.status(500).body("Failed to add submission to queue: " + e.getMessage());
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
