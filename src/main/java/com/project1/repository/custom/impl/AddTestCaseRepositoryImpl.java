package com.project1.repository.custom.impl;

import com.project1.entity.TestCaseEntity;
import com.project1.repository.AddTestCaseRepository;
import com.project1.repository.TestCaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class AddTestCaseRepositoryImpl implements AddTestCaseRepository {
    @Autowired
    private TestCaseRepository testCaseRepository;
    @Override
    public void addTestCase(TestCaseEntity testCaseEntity) {
        testCaseRepository.save(testCaseEntity);
    }
}
