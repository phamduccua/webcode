package com.project1.model.request;

import com.project1.model.dto.AbstractDTO;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class SubmissionRequest extends AbstractDTO {
    private Long id;
    SubmissionRequest(){
        this.setMaxPageItems(100);
    }
}
