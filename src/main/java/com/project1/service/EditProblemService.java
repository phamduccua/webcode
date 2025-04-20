package com.project1.service;

import com.project1.model.dto.ProblemDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface EditProblemService {
    ProblemDTO findByCode(String code);
    void updateProblem(ProblemDTO problemDTO, HttpServletRequest request);
}
