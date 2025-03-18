package com.project1.converter;

import com.project1.entity.ProblemEntity;
import com.project1.entity.UserEntity;
import com.project1.model.dto.ProblemDTO;
import com.project1.utils.ClassIdUtils;
import com.project1.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class ProblemEntityConverter {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SecurityUtils securityUtils;
    public ProblemEntity toProblemEntity(ProblemDTO problemDTO, HttpServletRequest request){
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
        UserEntity user = securityUtils.getUser(request);
        problemEntity.setCreatedBy(user.getId());
        problemEntity.setLanguage(langua.toString());
        problemEntity.setShow_test(problemDTO.isShow_test() == true ? 1 : 0);
        return problemEntity;
    }
}
