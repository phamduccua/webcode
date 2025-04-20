package com.project1.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data //toString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UserDTO extends AbstractDTO{
    private Long id;
    private String username;
    private String fullname;
    private String password;
    private Integer status;
    private String phone_number;
    private String role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<String> class_id =  new ArrayList<>();
}
