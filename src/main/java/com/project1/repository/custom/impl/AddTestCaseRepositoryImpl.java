package com.project1.repository.custom.impl;

import com.project1.entity.TestCaseEntity;
import com.project1.repository.AddTestCaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class AddTestCaseRepositoryImpl implements AddTestCaseRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void addTestCase(TestCaseEntity testCaseEntity) {
        entityManager.persist(testCaseEntity);
    }
}
