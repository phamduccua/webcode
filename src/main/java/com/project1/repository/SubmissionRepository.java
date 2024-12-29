package com.project1.repository;

import com.project1.entity.SubmissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<SubmissionEntity,Long> {
    SubmissionEntity findById(long id);
    List<SubmissionEntity> findByProblem_id(long problemId);
}
