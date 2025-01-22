package com.project1.service.impl;

import com.project1.converter.ProblemAddConverter;
import com.project1.entity.ProblemEntity;
import com.project1.model.dto.ProblemDTO;
import com.project1.repository.AddProblemRepository;
import com.project1.service.AddProblemService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddProblemServiceImpl implements AddProblemService {
    @Autowired
    private ProblemAddConverter problemAddConverter;
    @Autowired
    private AddProblemRepository addProblemRepository;
    @Override
    public void addProblem(ProblemDTO problemDTO, HttpServletRequest request) {
        ProblemEntity problemEntity = problemAddConverter.toProblemEntity(problemDTO,request);
        addProblemRepository.addProblem(problemEntity);
    }
}
