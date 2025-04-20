package com.project1.model.request;

import com.project1.model.dto.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ProblemByUserRequest extends AbstractDTO {
    private String name;
    private Long contestId;
    ProblemByUserRequest(){
        this.setMaxPageItems(100);
    }
}
