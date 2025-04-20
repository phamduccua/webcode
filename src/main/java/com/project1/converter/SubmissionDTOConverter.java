package com.project1.converter;

import com.project1.entity.ProblemEntity;
import com.project1.entity.SubmissionEntity;
import com.project1.model.dto.SubmissionDTO;
import com.project1.repository.ProblemRepository;
import com.project1.utils.LanguageUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubmissionDTOConverter {
    @Autowired
    private ModelMapper modelMapper;
    public SubmissionDTO toSubmissionDTO(SubmissionEntity submissionEntity){
        SubmissionDTO submissionDTO = modelMapper.map(submissionEntity, SubmissionDTO.class);
        ProblemEntity problemEntity = submissionEntity.getProblem();
        submissionDTO.setLanguage(LanguageUtils.unLanguage(submissionEntity.getLanguage()));
        submissionDTO.setProblemName(problemEntity.getTitle());
        submissionDTO.setProblemCode(problemEntity.getCode());
        submissionDTO.setTime(String.format("%.2f",submissionEntity.getExecutionTime()));
        submissionDTO.setTest_acept(submissionEntity.getTestAcept());
        submissionDTO.setShow_test(problemEntity.getShow_test() == 1 ? true : false);
        return submissionDTO;
    }
}
