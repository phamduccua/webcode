package com.project1.controller.web;

import com.project1.model.dto.ContestDTO;
import com.project1.service.ContestService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/")
public class UserContest {
    @Autowired
    private ContestService contestService;
    @GetMapping("api/contest")
    public ModelAndView listContest(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("web/contest");
        LocalDateTime now = LocalDateTime.now();
        List<ContestDTO> list = contestService.findAll(request);
        mav.addObject("listContest",list);
        mav.addObject("now",now);
        return mav;
    }
}
