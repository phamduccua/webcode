package com.project1.service;

import com.project1.model.dto.TestCaseDTO;

import java.util.Map;

public interface AddOrUpdateTestCaseService {
    public void addOrUpdateTestCase(TestCaseDTO testCaseDTO);
    void editTestExample(Map<String,String> map);
}
