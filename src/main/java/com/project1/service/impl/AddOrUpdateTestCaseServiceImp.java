package com.project1.service.impl;

import com.project1.converter.TestCaseEntityConverter;
import com.project1.entity.TestCaseEntity;
import com.project1.model.dto.TestCaseDTO;
import com.project1.repository.AddTestCaseRepository;
import com.project1.repository.EditTestCaseRepository;
import com.project1.repository.TestCaseRepository;
import com.project1.service.AddOrUpdateTestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class AddOrUpdateTestCaseServiceImp implements AddOrUpdateTestCaseService {
    @Autowired
    private AddTestCaseRepository addTestCaseRepository;
    @Autowired
    private TestCaseEntityConverter testCaseEntityConverter;
    @Autowired
    private TestCaseRepository testCaseRepository;
    @Autowired
    private EditTestCaseRepository editTestCaseRepository;
    @Override
    public void addOrUpdateTestCase(TestCaseDTO testCaseDTO) {
        if(testCaseDTO.getId() != null){
            TestCaseEntity testCaseEntity = testCaseRepository.findById(testCaseDTO.getId()).get();
            testCaseEntity = testCaseEntityConverter.toTestCaseEntity(testCaseDTO);
            editTestCaseRepository.editTestCase(testCaseEntity);
        }
        else{
            TestCaseEntity testCaseEntity = testCaseEntityConverter.toTestCaseEntity(testCaseDTO);
            addTestCaseRepository.addTestCase(testCaseEntity);
        }
    }
}
