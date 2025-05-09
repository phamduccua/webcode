package com.project1.repository.custom.impl;

import com.project1.repository.TopicRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public class TopicRepositoryImpl implements TopicRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<String> findTopic(long classId) {
        StringBuilder sql = new StringBuilder("SELECT DISTINCT p.topic \n FROM problem p\n WHERE p.topic NOT LIKE '%CONTEST%' AND class_id = " + classId);
        Query query = entityManager.createNativeQuery(sql.toString(),String.class);
        return (List<String>) query.getResultList();
    }
}
