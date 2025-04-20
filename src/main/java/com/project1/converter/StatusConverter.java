package com.project1.converter;

import com.project1.entity.ProblemEntity;
import com.project1.entity.SubmissionEntity;
import com.project1.entity.UserEntity;
import com.project1.model.response.StatusResponse;
import com.project1.utils.LanguageUtils;
import com.project1.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class StatusConverter {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SecurityUtils securityUtils;
    public StatusResponse toStatusResponse(SubmissionEntity submissionEntity) {
        StatusResponse statusResponse = modelMapper.map(submissionEntity, StatusResponse.class);
        ProblemEntity problemEntity = submissionEntity.getProblem();
        UserEntity userEntity = submissionEntity.getUser();
        statusResponse.setLanguage(LanguageUtils.unLanguage(submissionEntity.getLanguage()));
        statusResponse.setProblemName(problemEntity.getTitle());
        statusResponse.setProblemCode(problemEntity.getCode());
        statusResponse.setTime(String.format("%.2f",submissionEntity.getExecutionTime()));
        statusResponse.setUsername(userEntity.getUsername());
        statusResponse.setFullname(userEntity.getFullname());
        statusResponse.setTest_acept(submissionEntity.getTestAcept());
        statusResponse.setShow_test(problemEntity.getShow_test() == 1 ? true : false);
        return statusResponse;
    }
}
