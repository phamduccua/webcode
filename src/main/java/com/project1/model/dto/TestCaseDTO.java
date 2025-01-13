package com.project1.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TestCaseDTO extends AbstractDTO{
    private Long id;
    private Long problemId;
    private List<InputDTO> inputs;
    private OutputDTO output;
    private String type;
    private int numberFile;
    private String example;
}
