package com.project1.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestCaseDTO extends AbstractDTO{
    private Long id;
    private Long problemId;
    private String input;
    private String expected_output;
    private String example;
}
