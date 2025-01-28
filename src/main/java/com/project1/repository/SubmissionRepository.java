package com.project1.repository;

import com.project1.entity.SubmissionEntity;
import com.project1.model.dto.SubmissionDTO;
import com.project1.repository.custom.SubmissionRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<SubmissionEntity,Long>, SubmissionRepositoryCustom {
    SubmissionEntity findById(long id);
    List<SubmissionEntity> findByProblem_idAndUser_id(Long problemId,Long userId);
    @Query("SELECT DISTINCT s.status FROM SubmissionEntity s WHERE s.user.id = :userId AND s.problem.id = :problemId AND s.status IS NOT NULL")
    List<String> findDistinctStatusesByUserIdAndProblemId(Long userId, Long problemId);
    int countByUser_id(Long userId);
    int countAllBy();
    List<SubmissionEntity> findByProblem_id(Long problemId,Pageable pageable);
    int countByProblem_id(Long problemId);
}
