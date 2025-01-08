package com.project1.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Data //toString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class UserDTO extends AbstractDTO{
    private String username;
    private String fullname;
    private String password;
    private Integer status;
    private String role;
    private String roleName;
    private String roleCode;
}
