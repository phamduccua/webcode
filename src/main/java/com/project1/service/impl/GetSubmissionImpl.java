package com.project1.service.impl;

import com.project1.converter.SubmissionDTOConverter;
import com.project1.entity.SubmissionEntity;
import com.project1.model.dto.SubmissionDTO;
import com.project1.repository.SubmissionRepository;
import com.project1.service.GetSubmission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class GetSubmissionImpl implements GetSubmission {
    @Autowired
    private SubmissionRepository submissionRepository;
    @Autowired
    private SubmissionDTOConverter submissionDTOConverter;
    @Override
    public List<SubmissionDTO> getSub() {
        List<SubmissionEntity> list = submissionRepository.findAll();
        List<SubmissionDTO> subs = new ArrayList<>();
        for (SubmissionEntity submissionEntity : list) {
            SubmissionDTO submissionDTO = submissionDTOConverter.toSubmissionDTO(submissionEntity);
            subs.add(submissionDTO);
        }
        return subs;
    }
}
