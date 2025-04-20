package com.project1.utils;

import com.project1.component.JwtTokenUtil;
import com.project1.entity.UserEntity;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SecurityUtils {
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtil jwtTokenUtil;
    public UserEntity getUser(HttpServletRequest request) {
        String authHeader = extractToken(request);
        final String token = authHeader.substring(7);
        String username = jwtTokenUtil.extractUsername(token);
        UserEntity userDetails = (UserEntity) userDetailsService.loadUserByUsername(username);
        return userDetails;
    }
    private String extractToken(HttpServletRequest request) {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader;
        }
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if ("token".equals(cookie.getName())) {
                    return "Bearer " + cookie.getValue();
                }
            }
        }
        return null;
    }
}
