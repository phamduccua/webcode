package com.project1.repository.custom;

import com.project1.builder.ProblemSearchBuilder;
import com.project1.entity.ProblemEntity;
import com.project1.model.request.ProblemSearchRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProblemRepositoryCustom {
    List<ProblemEntity> findAll(ProblemSearchBuilder problemSearchBuilder);
}
