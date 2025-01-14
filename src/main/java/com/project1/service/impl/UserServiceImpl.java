package com.project1.service.impl;

import com.project1.component.JwtTokenUtil;
import com.project1.converter.UserConverter;
import com.project1.converter.UserResponseConverter;
import com.project1.customExceptions.DataNotFoundException;
import com.project1.entity.UserEntity;
import com.project1.model.dto.LoadUserDTO;
import com.project1.model.dto.UserDTO;
import com.project1.model.dto.UserupdatePassword;
import com.project1.model.request.UserSearchRequest;
import com.project1.model.response.UserResponse;
import com.project1.model.response.UserSearchResponse;
import com.project1.repository.ContestRepository;
import com.project1.repository.UserRepository;
import com.project1.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private ContestRepository contestRepository;
    @Autowired
    private UserResponseConverter userResponseConverter;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public String Role(String username) {
        UserEntity userEntity = userRepository.findByUsernameAndStatus(username,1).get();
        return userEntity.getRole();
    }

    @Override
    public UserEntity createUser(UserDTO userDTO) {
        String username = userDTO.getUsername();
        if(userRepository.existsByUsername(username)) {
            throw new DataIntegrityViolationException("Username already exists");
        }
        UserEntity newUser = userConverter.toUserEntity(userDTO);
        newUser.setPassword(passwordEncoder.encode("123456"));
        newUser.setStatus(1);
        newUser = userRepository.save(newUser);
        UserEntity tmp = userRepository.save(newUser);
        tmp.setUsername("USER_" + String.valueOf(newUser.getId()));
        userRepository.save(tmp);
        return tmp;
    }
    @Override
    public String login(String username, String password) throws Exception {
        Optional<UserEntity> user = userRepository.findByUsernameAndStatus(username,1);
        if(user == null) {
            throw new DataNotFoundException("Invalid username / password");
        }
        UserEntity userEntity = user.get();
        if(!passwordEncoder.matches(password, userEntity.getPassword())) {
            throw new BadCredentialsException("Thông tin tài khoản hoặc mật khẩu không chính xac !!");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username, password,
                userEntity.getAuthorities()
        );
        authenticationManager.authenticate(authenticationToken);
        return jwtTokenUtil.generateToken(userEntity);
    }

    @Override
    public List<UserSearchResponse> findAll(UserSearchRequest request, Pageable pageable) {
        if(request.getName() == null) {
            request.setRole("");
        }
        List<UserEntity> list = new ArrayList<>();
        if(request.getName() == null || request.getName().isEmpty()) {
            list = userRepository.findByRoleContainingAndStatus(request.getRole(),1,pageable);
        }
        else{
            list = userRepository.findByUsernameContainingOrFullnameContainingAndRoleAndStatus(request.getName(),request.getName(),request.getRole(),1,pageable);
        }
        List<UserSearchResponse> responses = new ArrayList<>();
        for(UserEntity userEntity : list) {
            UserSearchResponse userSearchResponse = userConverter.toUserSearchResponse(userEntity);
            responses.add(userSearchResponse);
        }
        return responses;
    }

    @Override
    public UserDTO findById(Long id) {
        UserEntity userEntity = userRepository.findById(id);
        return userConverter.toUserDTO(userEntity);
    }

    @Override
    public void delete(List<Long> ids) {
        for(Long id : ids) {
            UserEntity userEntity = userRepository.findById(id);
            userEntity.setStatus(0);
            userRepository.save(userEntity);
        }
    }

    @Override
    public void update(UserDTO userDTO) {
        UserEntity tmp = userRepository.findById(userDTO.getId());
        userDTO.setUsername(tmp.getUsername());
        userDTO.setPassword(tmp.getPassword());
        userDTO.setStatus(tmp.getStatus());
        userDTO.setCreatedAt(tmp.getCreatedAt());
        UserEntity userEntity = userConverter.toUserEntity(userDTO);
        userRepository.save(userEntity);
    }

    @Override
    public void resetPassword(UserDTO userDTO) {
        UserEntity tmp = userRepository.findById(userDTO.getId());
        userDTO.setUsername(tmp.getUsername());
        userDTO.setPassword(passwordEncoder.encode("123456"));
        userDTO.setStatus(tmp.getStatus());
        userDTO.setCreatedAt(tmp.getCreatedAt());
        UserEntity userEntity = userConverter.toUserEntity(userDTO);
        userRepository.save(userEntity);
    }

    @Override
    public int coutTotalItems(UserSearchRequest request) {
        if (request.getName() == null) {
            request.setRole("");
        }
        List<UserEntity> list = new ArrayList<>();
        if (request.getName() == null || request.getName().isEmpty()) {
            list = userRepository.findByRoleContainingAndStatus(request.getRole(), 1);
        } else {
            list = userRepository.findByUsernameContainingOrFullnameContainingAndRoleAndStatus(request.getName(), request.getName(), request.getRole(), 1);
        }
        return list.size();
    }

    @Override
    public void updatePassword(UserupdatePassword userupdatePassword) {
        UserEntity user = userRepository.findById(userupdatePassword.getId());
        if(!passwordEncoder.matches(userupdatePassword.getPassword(),user.getPassword())) {
            throw new BadCredentialsException("Mật khẩu hiện tại bạn nhập không chính xác !!");
        }
        if(!userupdatePassword.getNewPassword().equals(userupdatePassword.getRentPassword())){
            throw new BadCredentialsException("Mật khẩu mới và mật khẩu xác nhận không khớp !!");
        }
        user.setPassword(passwordEncoder.encode(userupdatePassword.getNewPassword()));
        userRepository.save(user);
    }

    @Override
    public LoadUserDTO loadUser(Long id) {
        UserEntity user = userRepository.findById(id);
        LoadUserDTO loadUserDTO = new LoadUserDTO();
        loadUserDTO.setFullname(user.getFullname());
        loadUserDTO.setUsername(user.getUsername());
        return loadUserDTO;
    }
    @Override
    public List<UserResponse> findByRole(String role, Long contestId,String fullname) {
        if(fullname == null) fullname = "";
        List<UserEntity> list = userRepository.findByRoleAndStatusAndFullnameContaining(role,1,fullname);
        List<UserResponse> result = new ArrayList<>();
        for(UserEntity userEntity : list) {
            UserResponse user = userResponseConverter.toUserResponse(userEntity);
            if(contestRepository.existsByIdAndUserEntities_Id(contestId, userEntity.getId()) == true){
                user.setChecked("checked");
            }
            result.add(user);
        }
        return result;
    }
}
