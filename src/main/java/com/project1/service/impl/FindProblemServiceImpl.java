package com.project1.service.impl;

import com.project1.converter.ProblemDTOConverter;
import com.project1.entity.ProblemEntity;
import com.project1.model.dto.ProblemDTO;
import com.project1.repository.ProblemRepository;
import com.project1.service.FindProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FindProblemServiceImpl implements FindProblemService {
    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private ProblemDTOConverter problemDTOConverter;
    @Override
    public ProblemDTO findByCode(String code) {
        ProblemEntity problemEntity = problemRepository.findByCode(code);
        ProblemDTO problemDTO = problemDTOConverter.toProblemDTO(problemEntity);
        return problemDTO;
    }
}
