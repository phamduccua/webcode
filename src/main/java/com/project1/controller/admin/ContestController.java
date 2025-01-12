package com.project1.controller.admin;
import com.project1.model.dto.ContestDTO;
import com.project1.model.dto.ProblemDTO;
import com.project1.service.ContestService;
import com.project1.service.EditProblemService;
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
    @Autowired
    private EditProblemService editProblemService;
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
    @GetMapping("/admin/contest-problem-{id}")
    public ModelAndView problemContest(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("admin/contest/contest_problem");
        ContestDTO contestDTO =  contestService.findContestById(id);
        List<ProblemDTO> list = contestService.findProblem(id);
        mav.addObject("contestDTO",contestDTO);
        mav.addObject("listProblem",list);
        return mav;
    }
    @GetMapping("/admin/contest-create_problem-{id}")
    public ModelAndView createProblemContest(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("admin/contest/contest_problem_create");
        ContestDTO contestDTO =  contestService.findContestById(id);
        mav.addObject("contestDTO",contestDTO);

        return mav;
    }
    @GetMapping("/admin/problem_contest-edit-{id}")
    public ModelAndView editProblemContest(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("admin/contest/problem_contest_edit");
        ProblemDTO problemDTO = editProblemService.findById(id);
        mav.addObject("problemDTO",problemDTO);
        return mav;
    }
}
