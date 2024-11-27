package com.project1.service.impl;

import com.project1.builder.ProblemSearchBuilder;
import com.project1.converter.ProblemSearchBuilderConverter;
import com.project1.converter.ProblemSearchConverter;
import com.project1.entity.ProblemEntity;
import com.project1.model.dto.ProblemDTO;
import com.project1.model.request.ProblemSearchRequest;
import com.project1.model.response.ProblemSearchReponse;
import com.project1.repository.ProblemRepository;
import com.project1.service.ProblemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProblemSearchServiceImpl implements ProblemSearchService {
    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private ProblemSearchConverter problemSearchConverter;
    @Autowired
    private ProblemSearchBuilderConverter problemSearchBuilderConverter;
    @Override
    public List<ProblemSearchReponse> findAll(ProblemSearchRequest problemSearchRequest) {
        ProblemSearchBuilder problemSearchBuilder = problemSearchBuilderConverter.toProblemSearchBuilder(problemSearchRequest);
        List<ProblemEntity> problemEntites = problemRepository.findAll(problemSearchBuilder);
        List<ProblemSearchReponse> result = new ArrayList<>();
        for (ProblemEntity problemEntity : problemEntites) {
            ProblemSearchReponse problemSearchReponse = problemSearchConverter.toProblemSearchReponse(problemEntity);
            result.add(problemSearchReponse);
        }
        return result;
    }
}
