package com.project1.converter;

import com.project1.entity.ProblemEntity;
import com.project1.entity.SubmissionEntity;
import com.project1.repository.ProblemRepository;
import com.project1.utils.LanguageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class SubmissionEntityConverter {
    @Autowired
    private ProblemRepository problemRepository;
    public SubmissionEntity toSubmissonEntity(String submitted, String language, Long problemId){
        SubmissionEntity submissionEntity = new SubmissionEntity();
        submissionEntity.setSubmitted(submitted);
        submissionEntity.setLanguage(LanguageUtils.language(language));
        ProblemEntity problemEntity = problemRepository.findById(problemId).get();
        submissionEntity.setProblem(problemEntity);
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        return submissionEntity;
    }
}
