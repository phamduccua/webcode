package com.project1.converter;

import com.project1.entity.ProblemEntity;
import com.project1.model.dto.ProblemDTO;
import com.project1.utils.ClassIdUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class ProblemEntityConverter {
    @Autowired
    private ModelMapper modelMapper;
    public ProblemEntity toProblemEntity(ProblemDTO problemDTO){
        ProblemEntity problemEntity = modelMapper.map(problemDTO, ProblemEntity.class);
        problemEntity.setClassId(ClassIdUtils.toClassId(problemDTO.getGroup()));
        return problemEntity;
    }
}
