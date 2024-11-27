package com.project1.converter;

import com.project1.entity.TestCaseEntity;
import com.project1.model.dto.TestCaseDTO;
import com.project1.repository.ProblemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class TestCaseEntityConverter {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ProblemRepository problemRepository;
    public TestCaseEntity toTestCaseEntity(TestCaseDTO testCaseDTO){
        TestCaseEntity testCaseEntity = modelMapper.map(testCaseDTO, TestCaseEntity.class);
        testCaseEntity.setProblem(problemRepository.findById(testCaseDTO.getProblemId()).orElse(null));
        return testCaseEntity;
    }
}
