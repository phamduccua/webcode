package com.project1.repository;

import com.project1.entity.ProblemEntity;
import com.project1.model.dto.ProblemDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface EditProblemRepository {
    void updateProblem(ProblemEntity problemEntity);
}
