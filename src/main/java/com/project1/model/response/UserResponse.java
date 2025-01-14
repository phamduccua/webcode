package com.project1.model.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class UserResponse {
    private Long id;
    private String fullname;
    private String username;
    private String checked;
}
