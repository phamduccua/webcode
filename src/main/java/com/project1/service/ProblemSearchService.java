package com.project1.service;

import com.project1.model.dto.ProblemDTO;
import com.project1.model.request.ProblemSearchRequest;
import com.project1.model.response.ProblemSearchReponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProblemSearchService {
    public List<ProblemSearchReponse> findAll(ProblemSearchRequest problemSearchRequest, Pageable pageable);
    int countTotalItems(ProblemSearchRequest problemSearchRequest);
}
