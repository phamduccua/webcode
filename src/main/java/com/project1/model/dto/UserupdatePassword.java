package com.project1.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserupdatePassword {
    private Long id;
    private String password;
    private String newPassword;
    private String rentPassword;
}
