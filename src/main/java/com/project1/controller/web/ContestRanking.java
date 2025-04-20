package com.project1.controller.web;

import com.project1.model.dto.ContestDTO;
import com.project1.model.response.LeaderBoardResponse;
import com.project1.service.ContestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class ContestRanking {
    @Autowired
    private ContestService contestService;
    @GetMapping("api/contest/{id}/ranking")
    public ModelAndView ranking(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("web/contest_ranking");
        ContestDTO contestDTO =  contestService.findContestById(id);
        LeaderBoardResponse leaderboard = contestService.leaderBoard(contestDTO);
        mav.addObject("contestDTO",contestDTO);
        mav.addObject("leaderboard",leaderboard);
        mav.addObject("id", id);
        return mav;
    }
}
