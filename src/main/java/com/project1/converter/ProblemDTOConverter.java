package com.project1.converter;

import com.project1.entity.ProblemEntity;
import com.project1.entity.ProgramingLanguageEntity;
import com.project1.model.dto.ProblemDTO;
import com.project1.utils.ClassIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProblemDTOConverter {
    @Autowired
    private ModelMapper modelMapper;
    public ProblemDTO toProblemDTO(ProblemEntity item){
        ProblemDTO problemDTO = modelMapper.map(item,ProblemDTO.class);
        problemDTO.setGroup(ClassIdUtils.toClassId(item.getClassId()));
        List<String> result = new ArrayList<>();
        List<ProgramingLanguageEntity> tmp = new ArrayList<>();
        for(ProgramingLanguageEntity programingLanguageEntity : item.getProgramingLanguages()){
            String it = programingLanguageEntity.getName();
            result.add(it);
        }
        problemDTO.setProgramingLanguage(result);
        return problemDTO;
    }
}
