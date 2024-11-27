package com.project1.service.impl;

import com.project1.entity.ProblemEntity;
import com.project1.service.DeleteProblemService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DeleteProblemServiceImpl implements DeleteProblemService {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void deleteProblem(Long problemId) {
        entityManager.remove(entityManager.find(ProblemEntity.class, problemId));
    }
}
