package com.project1.repository;

import com.project1.entity.TestCaseEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestCaseRepository extends JpaRepository<TestCaseEntity,Long> {
    List<TestCaseEntity> findByProblemId(Long problemId);
    TestCaseEntity findById(long id);
    List<TestCaseEntity> findByProblemIdAndExample(Long problemId, String example);
}
