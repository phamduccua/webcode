package com.project1.repository;

import com.project1.builder.ProblemSearchBuilder;
import com.project1.entity.ProblemEntity;
import com.project1.repository.custom.ProblemRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProblemRepository extends JpaRepository<ProblemEntity,Long>, ProblemRepositoryCustom {
    ProblemEntity findById(long id);
    ProblemEntity findByCode(String code);
    List<ProblemEntity> findByClassId(Long classId);
    List<ProblemEntity> findByTitleContainingAndCreatedBy(String title, Long createBy, Pageable pageable);
}
