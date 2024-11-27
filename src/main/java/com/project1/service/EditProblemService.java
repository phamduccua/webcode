package com.project1.service;

import com.project1.model.dto.ProblemDTO;

public interface EditProblemService {
    public ProblemDTO findById(Long id);
    public Long updateProblem(ProblemDTO problemDTO);
}
