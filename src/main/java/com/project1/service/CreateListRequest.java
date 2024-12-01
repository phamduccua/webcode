package com.project1.service;

import com.project1.entity.SubmissionEntity;
import com.project1.model.request.SubmitRequest;

import java.util.List;

public interface CreateListRequest {
    List<SubmitRequest> creates(SubmissionEntity submissionEntity);
}
