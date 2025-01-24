package com.project1.api.web;

import com.project1.entity.UserEntity;
import com.project1.model.dto.LoadUserDTO;
import com.project1.model.dto.UserDTO;
import com.project1.model.dto.UserLoginDTO;
import com.project1.model.dto.UserupdatePassword;
import com.project1.model.response.LoginResponse;
import com.project1.repository.UserRepository;
import com.project1.service.IUserService;
import com.project1.utils.SecurityUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
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
    @Autowired
    private SecurityUtils securityUtils;
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
    @PutMapping("api/update_password")
    public ResponseEntity<?> updatePassword(@Valid @RequestBody UserupdatePassword userupdate){
        try{
            userService.updatePassword(userupdate);
            return ResponseEntity.ok(userupdate);
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

    @PostMapping("api/loader")
    public ResponseEntity<?> loadUser(@Valid @RequestBody Long id){
        LoadUserDTO loadUser = userService.loadUser(id);
        return ResponseEntity.ok(loadUser);
    }

    @PostMapping("api/home")
    public ResponseEntity<?> homeAccount(HttpServletRequest request){
        try{
            UserEntity user = securityUtils.getUser(request);
            return ResponseEntity.ok(user.getId());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("admin/restore/{ids}")
    public ResponseEntity<?> restoreUser(@PathVariable List<Long> ids){
        try{
            userService.restoreUser(ids);
            return ResponseEntity.ok().build();
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("api/logout")
    public ResponseEntity<?> logout(HttpServletRequest request){
        try{
            userService.logout(request);
            return ResponseEntity.ok().build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
