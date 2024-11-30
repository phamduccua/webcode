package com.project1.repository;

import com.project1.entity.SubmissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<SubmissionEntity,Long> {
    SubmissionEntity findById(long id);
}
