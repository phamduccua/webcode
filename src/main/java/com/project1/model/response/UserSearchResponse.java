package com.project1.model.response;

import com.project1.model.dto.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class UserSearchResponse extends AbstractDTO {
    private Long id;
    private String username;
    private String fullname;
    private String phone_number;
    private String role;
}
