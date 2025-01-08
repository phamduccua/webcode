package com.project1.model.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class LoginResponse {
    public String token;
    public String role;
}
