package com.project1.controller.web;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/")
public class UserController {
    @GetMapping("login")
    public String login() {
        return "index";
    }
}
