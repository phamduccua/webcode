package com.project1.service;

import com.project1.entity.UserEntity;
import com.project1.model.dto.LoadUserDTO;
import com.project1.model.dto.UserDTO;
import com.project1.model.dto.UserupdatePassword;
import com.project1.model.request.UserSearchRequest;
import com.project1.model.response.UserResponse;
import com.project1.model.response.UserSearchResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    String Role(String username);
    UserEntity createUser(UserDTO userDTO);
    String login(String username, String password) throws Exception;
    List<UserSearchResponse> findAll(UserSearchRequest request, Pageable pageable);
    List<UserSearchResponse> findAllDelete(UserSearchRequest request, Pageable pageable);
    UserDTO findById(Long id);
    void delete(List<Long> ids);
    void update(UserDTO userDTO);
    void resetPassword(UserDTO userDTO);
    int coutTotalItems(UserSearchRequest request);
    int countTotalItemsDelete(UserSearchRequest request);
    void updatePassword(UserupdatePassword userupdatePassword);
    LoadUserDTO loadUser(Long id);
    List<UserResponse> findByRole(String role,Long contestId,String fullname);
    void restoreUser(List<Long> ids);
    void logout(HttpServletRequest httpServletRequest);
}
