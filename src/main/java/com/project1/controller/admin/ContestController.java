package com.project1.controller.admin;
import com.project1.model.dto.ContestDTO;
import com.project1.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/")
public class ContestController {
    @Autowired
    private ContestService contestService;
    @GetMapping("admin/list_contest")
    public ModelAndView createContest(){
        ModelAndView mav = new ModelAndView("admin/contest/list_contest");
        LocalDateTime now = LocalDateTime.now();
        List<ContestDTO> list = contestService.findAll();
        mav.addObject("listContest",list);
        mav.addObject("now",now);
        return mav;
    }
    @GetMapping("admin/contest-detail-{id}")
    public ModelAndView detailContest(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("admin/contest/contest_detail");
        ContestDTO contestDTO =  contestService.findContestById(id);
        mav.addObject("contestDTO",contestDTO);
        return mav;
    }
}
