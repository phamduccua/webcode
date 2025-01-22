package com.project1.repository.custom.impl;

import com.project1.entity.SubmissionEntity;
import com.project1.repository.custom.SubmissionRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class SubmissionRepositoryImpl implements SubmissionRepositoryCustom {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<SubmissionEntity> findSubmission(Long id, Pageable pageable) {
        StringBuilder sql = new StringBuilder("select s.* from submission s \n");
        sql.append("inner join problem p on s.problem_id = p.id \n");
        if (id != null) {
            sql.append("inner join user u on s.user_id = u.id \n");
        }
        sql.append("where p.type not like 'CONTEST' \n");
        if (id != null) {
            sql.append("and u.id = ").append(id).append(" \n");
        }
        sql.append("order by s.id desc \n");
        sql.append("limit ").append(pageable.getPageSize()).append(" \n");
        sql.append("offset ").append(pageable.getPageNumber() * pageable.getPageSize()).append(" \n");
        Query query = entityManager.createNativeQuery(sql.toString(), SubmissionEntity.class);
        return query.getResultList();
    }
}

