package com.project1.repository.custom;

import com.project1.builder.ProblemSearchBuilder;
import com.project1.entity.ProblemEntity;

import java.util.List;

public interface ProblemRepositoryCustom {
    List<ProblemEntity> findAll(ProblemSearchBuilder problemSearchBuilder);
}
