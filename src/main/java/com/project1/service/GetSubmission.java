package com.project1.service;

import com.project1.model.dto.SubmissionDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GetSubmission {
    List<SubmissionDTO> getSub(HttpServletRequest request,Pageable pageable);
    int countItems(HttpServletRequest request);
}
