package com.project1.model.request;

import com.project1.model.dto.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class UserSearchRequest extends AbstractDTO {
    private String name;
    private String role;
    UserSearchRequest(){
        this.setMaxPageItems(5);
    }
}
