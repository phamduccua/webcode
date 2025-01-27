package com.project1.controller.web;

import com.project1.model.dto.UserDTO;
import com.project1.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class UpdatePassword {
    @Autowired
    private IUserService userService;
    @GetMapping("api/update_password/{id}")
    public ModelAndView updatePassword(@PathVariable("id") Long id, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("web/updatePassword");
        UserDTO userDTO = userService.findById(id);
        mav.addObject("user", userDTO);
        mav.addObject("id",id);
        return mav;
    }
}
