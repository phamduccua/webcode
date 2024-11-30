package com.project1.service.impl;

import com.project1.converter.TestCaseDTOConverter;
import com.project1.entity.TestCaseEntity;
import com.project1.model.dto.TestCaseDTO;
import com.project1.repository.TestCaseRepository;
import com.project1.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TestCaseServiceImpl implements TestCaseService {
    @Autowired
    private TestCaseRepository testCaseRepository;
    @Autowired
    private TestCaseDTOConverter testCaseDTOConverter;
    @Override
    public List<TestCaseDTO> findByProblemId(Long problemId) {
        List<TestCaseEntity> testCaseEntites = testCaseRepository.findByProblemId(problemId);
        List<TestCaseDTO> result = new ArrayList<>();
        for(TestCaseEntity item : testCaseEntites){
            TestCaseDTO testCaseDTO = testCaseDTOConverter.toTesstCaseDTO(item);
            result.add(testCaseDTO);
        }
        return result;
    }

    @Override
    public List<TestCaseDTO> findByProblemIdAndExample(Long problemId, String example) {
        List<TestCaseEntity> testCaseEntites = testCaseRepository.findByProblemIdAndExample(problemId,example);
        List<TestCaseDTO> result = new ArrayList<>();
        for(TestCaseEntity item : testCaseEntites){
            TestCaseDTO testCaseDTO = testCaseDTOConverter.toTesstCaseDTO(item);
            result.add(testCaseDTO);
        }
        return result;
    }
}
