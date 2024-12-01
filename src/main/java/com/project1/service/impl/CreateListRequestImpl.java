package com.project1.service.impl;

import com.project1.entity.ProblemEntity;
import com.project1.entity.SubmissionEntity;
import com.project1.entity.TestCaseEntity;
import com.project1.model.request.SubmitRequest;
import com.project1.service.CreateListRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service

public class CreateListRequestImpl implements CreateListRequest {
    @Override
    public List<SubmitRequest> creates(SubmissionEntity submissionEntity) {
        List<SubmitRequest> submitRequests = new ArrayList<SubmitRequest>();
        ProblemEntity problemEntity = submissionEntity.getProblem();
        List<TestCaseEntity> listTest = problemEntity.getTestCases();
        for(TestCaseEntity testCaseEntity : listTest) {
            SubmitRequest submitRequest = new SubmitRequest();
            submitRequest.setStdin(testCaseEntity.getInput());
            submitRequest.setScript(submissionEntity.getSubmitted());
            submitRequest.setLanguage(submissionEntity.getLanguage());
            submitRequests.add(submitRequest);
        }
        return submitRequests;
    }
}
