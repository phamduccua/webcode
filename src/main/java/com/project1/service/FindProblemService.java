package com.project1.service;

import com.project1.model.dto.ProblemDTO;

public interface FindProblemService {
    ProblemDTO findByCode(String code);
}
