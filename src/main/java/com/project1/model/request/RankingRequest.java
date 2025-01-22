package com.project1.model.request;

import com.project1.model.dto.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RankingRequest extends AbstractDTO{
    private String group;
    RankingRequest(){
        this.setMaxPageItems(5);
    }
}
