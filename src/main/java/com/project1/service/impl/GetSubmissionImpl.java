package com.project1.service.impl;

import com.project1.converter.StatusConverter;
import com.project1.converter.SubmissionDTOConverter;
import com.project1.entity.SubmissionEntity;
import com.project1.entity.UserEntity;
import com.project1.model.dto.SubmissionDTO;
import com.project1.model.response.StatusResponse;
import com.project1.repository.SubmissionRepository;
import com.project1.service.GetSubmission;
import com.project1.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class GetSubmissionImpl implements GetSubmission {
    @Autowired
    private SubmissionRepository submissionRepository;
    @Autowired
    private SubmissionDTOConverter submissionDTOConverter;
    @Autowired
    private SecurityUtils securityUtils;
    @Autowired
    private StatusConverter statusConverter;
    @Override
    public List<SubmissionDTO> getSub(HttpServletRequest request, Pageable pageable) {
        UserEntity user = securityUtils.getUser(request);
        List<SubmissionEntity> list = submissionRepository.findSubmission(user.getId(),pageable);
        List<SubmissionDTO> subs = new ArrayList<>();
        for (SubmissionEntity submissionEntity : list) {
            SubmissionDTO submissionDTO = submissionDTOConverter.toSubmissionDTO(submissionEntity);
            subs.add(submissionDTO);
        }
        return subs;
    }

    @Override
    public int countItems(HttpServletRequest request) {
        UserEntity user = securityUtils.getUser(request);
        return submissionRepository.countByUser_id(user.getId());
    }

    @Override
    public int countAll(){
        return submissionRepository.countAllBy();
    }

    @Override
    public List<StatusResponse> getAll(Pageable pageable) {
        List<SubmissionEntity> listSubmission = submissionRepository.findSubmission(null,pageable);
        List<StatusResponse> subs = new ArrayList<>();
        for (SubmissionEntity submissionEntity : listSubmission) {
            StatusResponse statusResponse = statusConverter.toStatusResponse(submissionEntity);
            subs.add(statusResponse);
        }
        return subs;
    }
}
