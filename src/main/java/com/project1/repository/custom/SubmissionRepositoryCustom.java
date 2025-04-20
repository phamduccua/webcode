package com.project1.repository.custom;

import com.project1.entity.SubmissionEntity;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubmissionRepositoryCustom {
    List<SubmissionEntity> findSubmission(Long id, Pageable pageable);
    List<SubmissionEntity> findSubmissionContest(Long id, List<Long> problemId, Pageable pageable);
    int coutSubmission(Long id);
    int coutSubmissionContest(Long id, Long problemId);
}
