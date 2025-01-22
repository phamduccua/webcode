package com.project1.converter;

import com.project1.entity.ProblemEntity;
import com.project1.entity.UserEntity;
import com.project1.model.dto.ProblemContestDTO;
import com.project1.model.dto.ProblemDTO;
import com.project1.utils.ClassIdUtils;
import com.project1.utils.RanddomCodeProblem;
import com.project1.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class ProblemAddConverter {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RanddomCodeProblem randdomCodeProblem;
    @Autowired
    private SecurityUtils securityUtils;
    public ProblemEntity toProblemEntity(ProblemDTO problemDTO, HttpServletRequest request) {
        ProblemEntity problemEntity = modelMapper.map(problemDTO, ProblemEntity.class);
        problemEntity.setClassId(ClassIdUtils.toClassId(problemDTO.getGroup()));
        UserEntity user = securityUtils.getUser(request);
        problemEntity.setCreatedBy(user.getId());
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
    public ProblemEntity toProblemEntity(ProblemContestDTO problemContestDTO,HttpServletRequest request) {
        ProblemEntity problemEntity = modelMapper.map(problemContestDTO, ProblemEntity.class);
        if(problemContestDTO.getCode() == null) {
            problemEntity.setCode(randdomCodeProblem.generateUniqueCode());
        }
        UserEntity user = securityUtils.getUser(request);
        problemEntity.setCreatedBy(user.getId());
        return problemEntity;
    }
}
