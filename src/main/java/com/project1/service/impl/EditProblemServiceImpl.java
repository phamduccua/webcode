package com.project1.service.impl;

import com.project1.converter.ProblemDTOConverter;
import com.project1.converter.ProblemEntityConverter;
import com.project1.entity.ProblemEntity;
import com.project1.model.dto.ProblemDTO;
import com.project1.repository.EditProblemRepository;
import com.project1.repository.ProblemRepository;
import com.project1.service.EditProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditProblemServiceImpl implements EditProblemService {
    @Autowired
    private ProblemRepository problemRepository;
    @Autowired
    private ProblemDTOConverter problemDTOConverter;
    @Autowired
    private ProblemEntityConverter problemEntityConverter;
    @Autowired
    private EditProblemRepository editProblemRepository;
    @Override
    public ProblemDTO findByCode(String code) {
        ProblemEntity problemEntity = problemRepository.findByCode(code);
        ProblemDTO problemDTO = problemDTOConverter.toProblemDTO(problemEntity);
        return problemDTO;
    }
    @Override
    public Long updateProblem(ProblemDTO problemDTO) {
        ProblemEntity problemEntity = problemEntityConverter.toProblemEntity(problemDTO);
        editProblemRepository.updateProblem(problemEntity);
        return problemDTO.getId();
    }
}
