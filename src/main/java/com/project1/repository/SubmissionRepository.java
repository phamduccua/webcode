package com.project1.repository;
import com.project1.entity.SubmissionEntity;
import com.project1.repository.custom.SubmissionRepositoryCustom;
import org.springframework.data.repository.query.Param;
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
    @Query("SELECT s FROM SubmissionEntity s WHERE s.problem.id = :problemId ORDER BY s.id DESC")
    List<SubmissionEntity> findByProblem_id(@Param("problemId") Long problemId, Pageable pageable);
    int countByProblem_id(Long problemId);
}
