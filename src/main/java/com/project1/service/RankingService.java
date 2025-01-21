package com.project1.service;

import com.project1.model.request.RankingRequest;
import com.project1.model.response.RankingResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RankingService {
    List<RankingResponse> findAllRanking(RankingRequest rankingRequest,Pageable pageable);
    int countTotalItem(RankingRequest rankingRequest);
}
