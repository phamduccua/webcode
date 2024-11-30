package com.project1.api.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class UpdateStatus {
    @PostMapping("/update-status")
    public ResponseEntity<Void> updateStatus(@RequestBody Map<String, List<Long>> ids) {
        
        return ResponseEntity.ok().build();
    }
}
