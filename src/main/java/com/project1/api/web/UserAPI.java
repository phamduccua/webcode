package com.project1.api.web;

import com.project1.entity.UserEntity;
import com.project1.model.dto.UserDTO;
import com.project1.model.dto.UserLoginDTO;
import com.project1.model.response.LoginResponse;
import com.project1.service.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.FieldError;
import java.util.List;
@RestController
@RequestMapping("/")
public class UserAPI {
    @Autowired
    private IUserService userService;
    @PostMapping("admin/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO,
                                        BindingResult result) {
        try{
            if(result.hasErrors()){
                List<String> errorMessage = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessage);
            }
            UserEntity userEntity = userService.createUser(userDTO);
            return ResponseEntity.ok(userEntity);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("admin/update")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDTO userDTO){
        try{
            userService.update(userDTO);
            return ResponseEntity.ok(userDTO);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PutMapping("admin/reset")
    public ResponseEntity<?> resetUser(@Valid @RequestBody UserDTO userDTO){
        try{
            userService.resetPassword(userDTO);
            return ResponseEntity.ok(userDTO);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PostMapping("login")
    public ResponseEntity<?> login(
            @Valid @RequestBody UserLoginDTO userLoginDTO) {
        try {
            String token = userService.login(userLoginDTO.getUsername(), userLoginDTO.getPassword());
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setToken(token);
            loginResponse.setRole(userService.Role(userLoginDTO.getUsername()));
            return ResponseEntity.ok(loginResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping("admin/delete/{ids}")
    public ResponseEntity<Void> deleteUsers(@PathVariable List<Long> ids){
        userService.delete(ids);
        return ResponseEntity.ok().build();
    }
}
