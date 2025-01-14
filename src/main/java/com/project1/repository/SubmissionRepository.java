package com.project1.repository;

import com.project1.entity.SubmissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<SubmissionEntity,Long> {
    SubmissionEntity findById(long id);
    List<SubmissionEntity> findByProblem_id(long problemId);
    @Query("SELECT DISTINCT s.status FROM SubmissionEntity s WHERE s.user.id = :userId AND s.problem.id = :problemId")
    List<String> findDistinctStatusesByUserIdAndProblemId(Long userId,Long problemId);

}
