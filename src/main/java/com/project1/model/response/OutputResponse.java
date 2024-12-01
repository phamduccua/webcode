package com.project1.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class OutputResponse {
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
