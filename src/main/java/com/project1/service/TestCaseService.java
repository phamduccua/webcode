package com.project1.service;

import com.project1.model.dto.TestCaseDTO;

import java.util.List;

public interface TestCaseService {
    List<TestCaseDTO> findByProblemId(Long problemId);
    List<TestCaseDTO> findByProblemIdAndExample(Long problemId,String example);
    TestCaseDTO findById(Long id);
}
