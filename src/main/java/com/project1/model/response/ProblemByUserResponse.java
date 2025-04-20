package com.project1.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProblemByUserResponse {
    private int status = 0;
    private String name;
    private Long problemId;
}
