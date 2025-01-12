package com.project1.utils;

import com.project1.component.JwtTokenUtil;
import com.project1.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityUtils {
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    public UserEntity getUser(String token){
        final String username = jwtTokenUtil.extractUsername(token);
        UserEntity userDetails = (UserEntity) userDetailsService.loadUserByUsername(username);
        return userDetails;
    }
}
