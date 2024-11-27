package com.project1.service.impl;

import com.project1.entity.TestCaseEntity;
import com.project1.service.DeleteTestCaseService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional

public class DeleteTestCaseServiceImpl implements DeleteTestCaseService {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void deleteTestCase(Long testCaseId) {
        entityManager.remove(entityManager.find(TestCaseEntity.class,testCaseId));
    }
}
