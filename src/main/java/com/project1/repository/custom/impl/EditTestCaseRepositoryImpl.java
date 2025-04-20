package com.project1.repository.custom.impl;

import com.project1.entity.TestCaseEntity;
import com.project1.repository.EditTestCaseRepository;
import com.project1.repository.TestCaseRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class EditTestCaseRepositoryImpl implements EditTestCaseRepository {
   @Autowired
   private TestCaseRepository testCaseRepository;
    @Override
    public void editTestCase(TestCaseEntity testCaseEntity) {
        testCaseRepository.save(testCaseEntity);
    }
}
