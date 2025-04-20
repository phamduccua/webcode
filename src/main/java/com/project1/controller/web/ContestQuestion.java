package com.project1.controller.web;

import com.project1.entity.ContestEntity;
import com.project1.model.request.ProblemSearchRequest;
import com.project1.model.response.ProblemSearchReponse;
import com.project1.repository.ContestRepository;
import com.project1.service.ContestService;
import com.project1.service.ProblemSearchService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/")
public class ContestQuestion {
    @Autowired
    private ProblemSearchService problemSerachService;
    @Autowired
    private ContestRepository contestRepository;
    @GetMapping("api/contest/{id}/question")
    public ModelAndView listQuestion(@PathVariable Long id, @ModelAttribute ProblemSearchRequest problemSearchRequest, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("web/contest_problem");
        LocalDateTime now = LocalDateTime.now();
        ContestEntity contest = contestRepository.findContestById(id);
        if (!now.isBefore(contest.getStartTime()) && !now.isAfter(contest.getEndTime())) {
            List<ProblemSearchReponse> list = problemSerachService.findByContestId(id, request);
            mav.addObject("id", id);
            mav.addObject("modelSearch", problemSearchRequest);
            mav.addObject("problemList", list);
            return mav;
        } else {
            return new ModelAndView("web/not_found");
        }
    }
}
