package com.project1.converter;

import com.project1.entity.InputEntity;
import com.project1.entity.TestCaseEntity;
import com.project1.model.dto.InputDTO;
import com.project1.model.dto.TestCaseDTO;
import com.project1.repository.ProblemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class TestCaseEntityConverter {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private InputConverter inputConverter;
    public TestCaseEntity toTestCaseEntity(TestCaseDTO testCaseDTO){
        TestCaseEntity testCaseEntity = modelMapper.map(testCaseDTO, TestCaseEntity.class);
        List<InputEntity> list = new ArrayList<>();
        for(InputDTO inputDTO : testCaseDTO.getInputs()){
            InputEntity inputEntity = inputConverter.toInputEntity(inputDTO);
            inputEntity.setTestCases(testCaseEntity);
            list.add(inputEntity);
        }
        testCaseEntity.setInputs(list);
        testCaseEntity.setExpctedOutputFileContent(testCaseDTO.getOutput().getContentFile());
        testCaseEntity.setOutputFileName(testCaseDTO.getOutput().getFileName());
        return testCaseEntity;
    }
}
