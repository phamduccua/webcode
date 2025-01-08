package com.project1.service.impl;

import com.project1.component.JwtTokenUtil;
import com.project1.customExceptions.DataNotFoundException;
import com.project1.entity.UserEntity;
import com.project1.model.dto.UserDTO;
import com.project1.repository.UserRepository;
import com.project1.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
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
        UserEntity newUser = UserEntity.builder()
                .username(userDTO.getUsername())
                .fullname(userDTO.getFullname())
                .role(userDTO.getRole())
                .status(1)
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();
        return userRepository.save(newUser);
    }
    @Override
    public String login(String username, String password) throws Exception {
        Optional<UserEntity> user = userRepository.findByUsernameAndStatus(username,1);
        if(user == null) {
            throw new DataNotFoundException("Invalid username / password");
        }
        UserEntity userEntity = user.get();
        if(!passwordEncoder.matches(password, userEntity.getPassword())) {
            throw new BadCredentialsException("Wrong username or password");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username, password,
                userEntity.getAuthorities()
        );
        authenticationManager.authenticate(authenticationToken);
        return jwtTokenUtil.generateToken(userEntity);
    }
}
