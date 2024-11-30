package com.project1.service.impl;

import com.project1.api.admin.RunCode;
import com.project1.entity.ProblemEntity;
import com.project1.entity.SubmissionEntity;
import com.project1.entity.TestCaseEntity;
import com.project1.model.request.SubmitRequest;
import com.project1.model.response.OuputResponse;
import com.project1.repository.AddOrUpdateSubRepository;
import com.project1.repository.TestCaseRepository;
import com.project1.service.SubmitService;
import com.project1.utils.CompareOuput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class SubmitServiceImpl implements SubmitService {
    @Autowired
    private AddOrUpdateSubRepository addOrUpdateSubRepository;
    @Autowired
    private TestCaseRepository testCaseRepository;
    @Override
    public void submit(SubmissionEntity submission) {
        if(updateStatus(submission) == true){
            submission.setStatus(1);
            addOrUpdateSubRepository.addOrUpdateSub(submission);
        }
        else{
            submission.setStatus(2);
            addOrUpdateSubRepository.addOrUpdateSub(submission);
        }
    }
    private static boolean updateStatus(SubmissionEntity submission) {
        ProblemEntity problem = submission.getProblem();
        List<TestCaseEntity> testCaseEntites = problem.getTestCases();
        int tongTest = testCaseEntites.size();
        int countTest = 0;
        for(TestCaseEntity testCase : testCaseEntites) {
            SubmitRequest submitRequest = new SubmitRequest();
            submitRequest.setScript(submission.getSubmitted());
            submitRequest.setLanguage(submission.getLanguage());
            submitRequest.setStdin(testCase.getInput());
            RunCode runCode = new RunCode();
            runCode.runCode(submitRequest);
//            if(output.getCompilationStatus() == null){
//                if(CompareOuput.compareOutput(output.getOutput(),testCase.getExpected_output()) == false) return false;
//                countTest++;
//            }
        }
        return true;
    }
}
