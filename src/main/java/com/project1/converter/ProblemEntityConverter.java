package com.project1.converter;

import com.project1.entity.ProblemEntity;
import com.project1.model.dto.ProblemDTO;
import com.project1.utils.ClassIdUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class ProblemEntityConverter {
    @Autowired
    private ModelMapper modelMapper;
    public ProblemEntity toProblemEntity(ProblemDTO problemDTO){
        ProblemEntity problemEntity = modelMapper.map(problemDTO, ProblemEntity.class);
        problemEntity.setClassId(ClassIdUtils.toClassId(problemDTO.getGroup()));
        List<String> langs = problemDTO.getLanguage();
        StringBuilder langua = new StringBuilder();
        if(langs != null){
            for(int i = 0;i<langs.size();i++){
                if(i != langs.size()-1){
                    langua.append(langs.get(i)).append(",");
                }
                else langua.append(langs.get(i));
            }
        }
        problemEntity.setLanguage(langua.toString());
        return problemEntity;
    }
}
