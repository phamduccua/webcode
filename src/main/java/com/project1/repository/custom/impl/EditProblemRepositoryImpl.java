package com.project1.repository.custom.impl;

import com.project1.entity.ProblemEntity;
import com.project1.model.dto.ProblemDTO;
import com.project1.repository.EditProblemRepository;
import com.project1.repository.ProblemRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Transactional
@Repository
public class EditProblemRepositoryImpl implements EditProblemRepository {
    @Autowired
    private ProblemRepository problemRepository;
    @Override
    public void updateProblem(ProblemEntity problemEntity) {
        problemRepository.save(problemEntity);
    }
}
