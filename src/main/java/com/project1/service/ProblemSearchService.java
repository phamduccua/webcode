package com.project1.service;

import com.project1.model.dto.ProblemDTO;
import com.project1.model.request.ProblemSearchRequest;
import com.project1.model.response.ProblemSearchReponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProblemSearchService {
    List<ProblemSearchReponse> findAll(ProblemSearchRequest problemSearchRequest, HttpServletRequest request, Pageable pageable);
    int countTotalItems(ProblemSearchRequest problemSearchRequest);
    List<ProblemSearchReponse> findByContestId(Long id, HttpServletRequest request);
}
