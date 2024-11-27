package com.project1.service.impl;

import com.project1.converter.TestCaseDTOConverter;
import com.project1.entity.TestCaseEntity;
import com.project1.model.dto.TestCaseDTO;
import com.project1.repository.TestCaseRepository;
import com.project1.service.EditTestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class EditTestCaseServiceImpl implements EditTestCaseService {
    @Autowired
    private TestCaseRepository testCaseRepository;
    @Autowired
    private TestCaseDTOConverter testCaseDTOConverter;
    @Override
    public TestCaseDTO findById(Long id) {
        TestCaseEntity testCaseEntity = testCaseRepository.findById(id).get();
        TestCaseDTO testCaseDTO = testCaseDTOConverter.toTesstCaseDTO(testCaseEntity);
        return testCaseDTO;
    }
}
