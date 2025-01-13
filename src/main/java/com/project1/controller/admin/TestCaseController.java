package com.project1.controller.admin;

import com.project1.model.dto.ProblemDTO;
import com.project1.model.dto.TestCaseDTO;
import com.project1.service.EditProblemService;
import com.project1.service.TestCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class TestCaseController {
    @Autowired
    private EditProblemService editProblemService;
    @Autowired
    private TestCaseService testCaseService;
    @GetMapping("admin/add_testcase-problem-{code}")
    public ModelAndView addTestCaseProblemContest(@PathVariable String code){
        ModelAndView mav = new ModelAndView("admin/testcase/add_testcase");
        ProblemDTO problemDTO = editProblemService.findByCode(code);
        mav.addObject("problemDTO",problemDTO);
        return mav;
    }
    @GetMapping("admin/edit_testcase-{id}")
    public ModelAndView editTestContest(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("admin/testcase/edit_testcase");
        TestCaseDTO testCaseDTO = testCaseService.findById(id);
        Long problemId = testCaseDTO.getProblemId();
        mav.addObject("testCaseDTO",testCaseDTO);
        mav.addObject("problemId",problemId);
        return mav;
    }
}
