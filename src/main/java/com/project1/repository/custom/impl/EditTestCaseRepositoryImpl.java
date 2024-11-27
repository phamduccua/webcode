package com.project1.repository.custom.impl;

import com.project1.entity.TestCaseEntity;
import com.project1.repository.EditTestCaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class EditTestCaseRepositoryImpl implements EditTestCaseRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void editTestCase(TestCaseEntity testCaseEntity) {
        entityManager.merge(testCaseEntity);
    }
}
