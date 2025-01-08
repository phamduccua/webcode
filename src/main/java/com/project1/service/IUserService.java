package com.project1.service;

import com.project1.entity.UserEntity;
import com.project1.model.dto.UserDTO;
public interface IUserService {
    String Role(String username);
    UserEntity createUser(UserDTO userDTO);
    String login(String username, String password) throws Exception;
}
