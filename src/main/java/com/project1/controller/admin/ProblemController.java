package com.project1.controller.admin;

import com.project1.entity.enums.difficulty;
import com.project1.entity.enums.group;
import com.project1.model.dto.ProblemDTO;
import com.project1.model.dto.TestCaseDTO;
import com.project1.model.request.ProblemSearchRequest;
import com.project1.model.response.ProblemSearchReponse;
import com.project1.service.*;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequestMapping("/")
public class ProblemController {
    @Autowired
    private ProblemSearchService problemSerachService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private EditProblemService editProblemService;
    @Autowired
    private TestCaseService testCaseService;
    @Autowired
    private FindProblemService findProblemService;
    @GetMapping("admin/list")
    public ModelAndView problemList(@ModelAttribute ProblemSearchRequest problemSearchRequest , HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/problem/list");
        mav.addObject("modelSearch", problemSearchRequest);
        List<ProblemSearchReponse> list = problemSerachService.findAll(problemSearchRequest);
        List<String> listTopic = topicService.findTopic();
        mav.addObject("problemList", list);
        mav.addObject("listGroup", group.type());
        mav.addObject("listTopic", listTopic);
        return mav;
    }

    @GetMapping("admin/add")
    public ModelAndView problemEdit(@ModelAttribute("problemAdd") ProblemDTO problemDTO , HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/problem/add");
        mav.addObject("problemAdd", problemDTO);
        mav.addObject("listGroup", group.type());
        mav.addObject("listDifficulty", difficulty.type());
        return mav;
    }

    @GetMapping("admin/detail-{id}")
    public ModelAndView problemEit(@PathVariable("id") Long id , HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/problem/edit-detail");
        mav.addObject("id",id);
        ProblemDTO problemDTO = editProblemService.findById(id);
        mav.addObject("nameProblem", problemDTO.getTitle());
        mav.addObject("problemEdit", problemDTO);
        mav.addObject("listGroup", group.type());
        mav.addObject("listDifficulty", difficulty.type());
        return mav;
    }

    @GetMapping("/admin/list-testcase-{id}")
    public ModelAndView problemAddTest(@PathVariable("id") Long id , HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/problem/edit-testcase");
        mav.addObject("id",id);
        ProblemDTO problemDTO = editProblemService.findById(id);
        List<TestCaseDTO> testCaseDTO = testCaseService.findByProblemId(id);
        mav.addObject("nameProblem", problemDTO.getTitle());
        mav.addObject("listTest",testCaseDTO);
        return mav;
    }

    @GetMapping("/admin/assignment-{code}")
    public ModelAndView assignment(@PathVariable("code") String code , HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/problem/assignment");
        ProblemDTO problemDTO = findProblemService.findByCode(code);
        List<TestCaseDTO> listTest = testCaseService.findByProblemIdAndExample(problemDTO.getId(),"check");
        mav.addObject("detail", problemDTO);
        mav.addObject("listTest", listTest);
        return mav;
    }
}
