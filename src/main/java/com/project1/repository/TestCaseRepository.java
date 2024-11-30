package com.project1.repository;

import com.project1.entity.TestCaseEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TestCaseRepository extends JpaRepository<TestCaseEntity,Long> {
    List<TestCaseEntity> findByProblemId(Long problemId);
    TestCaseEntity findById(long id);
    List<TestCaseEntity> findByProblemIdAndExample(Long problemId, String example);
}
