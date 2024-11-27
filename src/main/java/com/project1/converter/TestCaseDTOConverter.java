package com.project1.converter;

import com.project1.entity.TestCaseEntity;
import com.project1.model.dto.TestCaseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class TestCaseDTOConverter {
    @Autowired
    private ModelMapper modelMapper;
    public TestCaseDTO toTesstCaseDTO(TestCaseEntity testCaseEntity){
        TestCaseDTO testCaseDTO = modelMapper.map(testCaseEntity, TestCaseDTO.class);
        return testCaseDTO;
    }
}
