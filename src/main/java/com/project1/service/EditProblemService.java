package com.project1.service;

import com.project1.model.dto.ProblemDTO;

public interface EditProblemService {
    public ProblemDTO findByCode(String code);
    void updateProblem(ProblemDTO problemDTO);
}
