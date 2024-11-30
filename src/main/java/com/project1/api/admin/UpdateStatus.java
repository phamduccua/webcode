package com.project1.api.admin;

import com.project1.converter.SubmissionDTOConverter;
import com.project1.entity.SubmissionEntity;
import com.project1.model.dto.SubmissionDTO;
import com.project1.repository.SubmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class UpdateStatus {
    @Autowired
    private SubmissionRepository submissionRepository;
    @Autowired
    private SubmissionDTOConverter submissionDTOConverter;
    @PostMapping("/update-status")
    public ResponseEntity<List<SubmissionDTO>> updateStatus(@RequestBody Map<String, List<Long>> ids) {
        List<Long> list = ids.get("ids");
        List<SubmissionDTO> submissionDTOS = new ArrayList<>();
        for(Long id : list){
            SubmissionEntity submissionEntity = submissionRepository.findById(id).get();
            SubmissionDTO sub =  submissionDTOConverter.toSubmissionDTO(submissionEntity);
            submissionDTOS.add(sub);
        }
        return ResponseEntity.ok(submissionDTOS);
    }
}
