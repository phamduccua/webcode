package com.project1.config;

import com.project1.fillter.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login","/WEB-INF/views/*","/api/uploads","/api/logout").permitAll()
                        .requestMatchers("/admin/register","/admin/update","/admin/reset","/admin/delete/**","/admin/restore/**").hasRole("MANAGER")
                        .requestMatchers("/admin/**").hasAnyRole("MANAGER","ADMIN")
                        .requestMatchers("/api/*").hasAnyRole("ADMIN","USER","MANAGER")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
