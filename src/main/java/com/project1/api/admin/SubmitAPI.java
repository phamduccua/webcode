package com.project1.api.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/uploads/")
public class SubmitAPI {

    private final String UPLOAD_DIR = "D:/webcode/uploads/";

    @PostMapping("/file")
    public ResponseEntity<String> submit(@RequestParam("file") MultipartFile file) throws IOException {
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
}
