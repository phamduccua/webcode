package com.project1.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InputDTO {
    private Long id;
    private String fileName;
    private String contentFile;
    private Long testcase_id;
}
