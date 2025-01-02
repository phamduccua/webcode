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
    public SubmissionEntity toSubmissonEntity(String submitted, String language, Long problemId,String fileName){
        SubmissionEntity submissionEntity = new SubmissionEntity();
        if(language.equals("Java")){
            if(submitted.contains("public class")){
                submitted = submitted.replace("public class " + fileName,"public class solution ");
            }
            else{
                submissionEntity.setStatus(3);
                submissionEntity.setCode("CE");
                submissionEntity.setError("No public class: your main class must be declared as a \"public class\"");
            }
            if(submitted.contains("package")){
                submitted = submitted.replaceAll("^package\\s+[a-zA-Z_][a-zA-Z0-9_]*\\s*;\\s*", "");
            }
        }
        submissionEntity.setSubmitted(submitted);
        submissionEntity.setLanguage(LanguageUtils.language(language));
        ProblemEntity problemEntity = problemRepository.findById(problemId).get();
        submissionEntity.setProblem(problemEntity);
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        submissionEntity.setSubmittedAt(timestamp);
        return submissionEntity;
    }
}
