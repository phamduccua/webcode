package com.project1.repository.custom.impl;

import com.project1.entity.ProblemEntity;
import com.project1.repository.AddProblemRepository;
import com.project1.repository.ProblemRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class AddProblemRepositoryImpl implements AddProblemRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ProblemRepository problemRepository;
    @Override
    public Long addProblem(ProblemEntity problemEntity) {
        if (problemRepository.findByCode(problemEntity.getCode()) != null) {
            throw new IllegalArgumentException("Mã bài tập đã tồn tại: " + problemEntity.getCode());
        }
        entityManager.persist(problemEntity);
        ProblemEntity problemEntityNews = problemRepository.findByCode(problemEntity.getCode());
        return problemEntityNews.getId();
    }
}
