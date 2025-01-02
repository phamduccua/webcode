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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProblemSearchServiceImpl implements ProblemSearchService {
    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private ProblemSearchConverter problemSearchConverter;
    @Autowired
    private ProblemSearchBuilderConverter problemSearchBuilderConverter;
    @Override
    public List<ProblemSearchReponse> findAll(ProblemSearchRequest problemSearchRequest, Pageable pageable) {
        ProblemSearchBuilder problemSearchBuilder = problemSearchBuilderConverter.toProblemSearchBuilder(problemSearchRequest);
        List<ProblemEntity> problemEntites = problemRepository.findAll(problemSearchBuilder);
        Page<ProblemEntity> pages = page(problemEntites, pageable);
        List<ProblemSearchReponse> result = new ArrayList<>();
        for (ProblemEntity problemEntity : pages) {
            ProblemSearchReponse problemSearchReponse = problemSearchConverter.toProblemSearchReponse(problemEntity);
            result.add(problemSearchReponse);
        }
        return result;
    }

    public Page<ProblemEntity> page(List<ProblemEntity> list, Pageable pageable) {
        int offset = (int) pageable.getOffset();
        int pageSize = pageable.getPageSize();
        int total = list.size();
        List<ProblemEntity> pagedProblems = list.stream()
                .skip(offset)
                .limit(pageSize)
                .collect(Collectors.toList());
        return new PageImpl<>(pagedProblems, pageable, total);
    }

    public int countTotalItems(ProblemSearchRequest problemSearchRequest) {
        ProblemSearchBuilder problemSearchBuilder = problemSearchBuilderConverter.toProblemSearchBuilder(problemSearchRequest);
        return problemRepository.findAll(problemSearchBuilder).size();
    }
}
