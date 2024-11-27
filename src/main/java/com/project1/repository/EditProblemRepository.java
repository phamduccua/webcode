package com.project1.repository;

import com.project1.entity.ProblemEntity;
import com.project1.model.dto.ProblemDTO;

public interface EditProblemRepository {
    public void updateProblem(ProblemEntity problemEntity);
}
