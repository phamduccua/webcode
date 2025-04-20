package com.project1.api.admin;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/test")
public class TestAPI {
    @GetMapping()
    public void test() {
        System.out.println("test");
    }
}
