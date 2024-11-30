package com.project1.repository.custom.impl;

import com.project1.entity.SubmissionEntity;
import com.project1.repository.AddOrUpdateSubRepository;
import com.project1.repository.SubmissionRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class AddOrUpdateSubRepositoryImpl implements AddOrUpdateSubRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private SubmissionRepository submissionRepository;
    @Override
    public Long addOrUpdateSub(SubmissionEntity submissionEntity) {
        if(submissionEntity.getId() == null){
            entityManager.persist(submissionEntity);
        }
        else{
            SubmissionEntity sub = entityManager.merge(submissionEntity);
        }
        return submissionEntity.getId();
    }
}
