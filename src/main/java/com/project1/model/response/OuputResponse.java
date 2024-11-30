package com.project1.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OuputResponse {
    private String output;
    private String error;
    private Integer statusCode;
    private String memory;
    private String cpuTime;
    private Integer compilationStatus;
    private String projectKey;
    private boolean isExecutionSuccess;
    private boolean isCompiled;
}
