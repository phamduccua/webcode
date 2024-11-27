package com.project1.model.response;

import com.project1.model.dto.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProblemSearchReponse extends AbstractDTO {
    private String code;
    private String title;
    private String description;
    private String type;
    private String topic;
    private Integer difficul;
    private String inputFormat;
    private String outputFormat;
    private String example;
    private String group;
    private String constraints;
    private String color;
}
