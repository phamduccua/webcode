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
import java.util.StringTokenizer;

@Component
public class ProblemDTOConverter {
    @Autowired
    private ModelMapper modelMapper;
    public ProblemDTO toProblemDTO(ProblemEntity item){
        ProblemDTO problemDTO = modelMapper.map(item,ProblemDTO.class);
        problemDTO.setGroup(ClassIdUtils.toClassId(item.getClassId()));
        List<String> arr = new ArrayList<>();
        if(item.getLanguage() == null || item.getLanguage().equals("")) {
            System.out.println("null");
        }
        else{
            StringTokenizer str = new StringTokenizer(item.getLanguage(), ",");
            while(str.hasMoreTokens()){
                String language = str.nextToken();
                arr.add(language);
            }
        }
        problemDTO.setLanguage(arr);
        problemDTO.setShow_test(item.getShow_test() == 1 ? true : false);
        return problemDTO;
    }
}
