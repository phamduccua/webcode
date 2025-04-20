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
        sql.append("where p.type != 'CONTEST' \n");
        if (id != null) {
            sql.append("and u.id = ").append(id).append(" \n");
        }
        sql.append(" order by s.id desc \n");
        sql.append("limit ").append(pageable.getPageSize()).append(" \n");
        sql.append("offset ").append(pageable.getPageNumber() * pageable.getPageSize()).append(" \n");
        Query query = entityManager.createNativeQuery(sql.toString(), SubmissionEntity.class);
        return query.getResultList();
    }
    @Override
    public List<SubmissionEntity> findSubmissionContest(Long userId, List<Long> problemId, Pageable pageable) {
        StringBuilder sql = new StringBuilder("select s.* from submission s \n");
        sql.append("inner join problem p on s.problem_id = p.id \n");
        if (userId != null) {
            sql.append("inner join user u on s.user_id = u.id \n");
        }
        sql.append("where p.type = 'CONTEST' \n");
        sql.append("and s.user_id = ").append(userId).append(" \n");
        if(problemId.size() > 0) {
            sql.append(" and (\n");
            for(int i = 0;i<problemId.size();i++) {

                if(i != problemId.size() - 1){
                    sql.append(" s.problem_id = " + problemId.get(i) + " or ");
                }
                else{
                    sql.append(" s.problem_id = " + problemId.get(i) + " ) ");
                }
            }
        }
        sql.append(" order by s.id desc \n");
        sql.append("limit ").append(pageable.getPageSize()).append(" \n");
        sql.append("offset ").append(pageable.getPageNumber() * pageable.getPageSize()).append(" \n");
        Query query = entityManager.createNativeQuery(sql.toString(), SubmissionEntity.class);
        return query.getResultList();
    }

    @Override
    public int coutSubmission(Long id){
        StringBuilder sql = new StringBuilder("select count(s.id) from submission s \n" +
                "inner join user u on s.user_id = u.id\n" +
                "inner join problem p on s.problem_id = p.id\n" +
                "where p.type != 'CONTEST' \n");
        if(id != null){
            sql.append(" and u.id = ").append(id).append(" \n");
        }
        Query query = entityManager.createNativeQuery(sql.toString());
        Object result = query.getSingleResult();
        return ((Number) result).intValue();
    }

    @Override
    public int coutSubmissionContest(Long userId, Long problemId) {
        StringBuilder sql = new StringBuilder("select count(s.id) as result from submission s\n" +
                "inner join user u on s.user_id = u.id\n" +
                "inner join problem p on s.problem_id = p.id\n" +
                "where p.type = 'CONTEST' \n");
        sql.append("and s.user_id = ").append(userId).append(" \n");
        sql.append(" and s.problem_id = ").append(problemId).append(" \n");
        Query query = entityManager.createNativeQuery(sql.toString());
        Object result = query.getSingleResult();
        return ((Number) result).intValue();
    }
}

