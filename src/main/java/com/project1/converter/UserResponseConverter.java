package com.project1.converter;

import com.project1.entity.UserEntity;
import com.project1.model.response.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserResponseConverter {
    @Autowired
    private ModelMapper modelMpper;

    public UserResponse toUserResponse(UserEntity userEntity) {
        return modelMpper.map(userEntity, UserResponse.class);
    }
}
