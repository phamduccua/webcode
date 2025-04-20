package com.project1.converter;

import com.project1.entity.InputEntity;
import com.project1.entity.TestCaseEntity;
import com.project1.model.dto.InputDTO;
import com.project1.model.dto.OutputDTO;
import com.project1.model.dto.TestCaseDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class TestCaseDTOConverter {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private InputConverter inputConverter;
    public TestCaseDTO toTesstCaseDTO(TestCaseEntity testCaseEntity){
        TestCaseDTO testCaseDTO = modelMapper.map(testCaseEntity, TestCaseDTO.class);
        List<InputDTO> list = new ArrayList<>();
        for(InputEntity item : testCaseEntity.getInputs()){
            InputDTO inputDTO = inputConverter.toInputDTO(item);
            list.add(inputDTO);
        }
        testCaseDTO.setInputs(list);
        OutputDTO output = new OutputDTO();
        output.setFileName(testCaseEntity.getOutputFileName());
        output.setContentFile(testCaseEntity.getExpctedOutputFileContent());
        testCaseDTO.setOutput(output);
        return testCaseDTO;
    }
}
