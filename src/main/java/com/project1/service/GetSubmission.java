package com.project1.service;

import com.project1.model.dto.SubmissionDTO;
import com.project1.model.response.StatusResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GetSubmission {
    List<SubmissionDTO> getSub(HttpServletRequest request,Pageable pageable);
    int countItems(HttpServletRequest request);
    List<StatusResponse> getAll(Pageable pageable);
    int countAll();
    List<SubmissionDTO> listSubmissionContest(HttpServletRequest request, Long contestId, Pageable pageable);
    int countItemsContest(HttpServletRequest request, Long contestId);
    List<StatusResponse> getByProblem(Long problemId, Pageable pageable);
    int countByProbem(Long problemId);
}
