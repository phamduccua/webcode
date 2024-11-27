package com.project1.converter;

import com.project1.entity.ProblemEntity;
import com.project1.model.dto.ProblemDTO;
import com.project1.utils.ClassIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

@Component
public class ProblemDTOConverter {
    @Autowired
    private ModelMapper modelMapper;
    public ProblemDTO toProblemDTO(ProblemEntity item){
        ProblemDTO problemDTO = modelMapper.map(item,ProblemDTO.class);
        problemDTO.setGroup(ClassIdUtils.toClassId(item.getClassId()));
        return problemDTO;
    }
}
