package com.project1.controller.admin;

import com.project1.converter.SubmissionDTOConverter;
import com.project1.entity.SubmissionEntity;
import com.project1.entity.UserEntity;
import com.project1.model.dto.ProblemDTO;
import com.project1.model.dto.SubmissionDTO;
import com.project1.model.dto.TestCaseDTO;
import com.project1.repository.SubmissionRepository;
import com.project1.service.FindProblemService;
import com.project1.service.TestCaseService;
import com.project1.utils.LanguageUtils;
import com.project1.utils.ReverseList;
import com.project1.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/")
public class AdminSolution {
    @Autowired
    private SecurityUtils securityUtils;
    @Autowired
    private FindProblemService findProblemService;
    @Autowired
    private TestCaseService testCaseService;
    @Autowired
    private SubmissionRepository submissionRepository;
    @Autowired
    private SubmissionDTOConverter submissionDTOConverter;
    @GetMapping("admin/assignment-{code}")
    public ModelAndView assignment(@PathVariable("code") String code , HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/problem/assignment");
        ProblemDTO problemDTO = findProblemService.findByCode(code);
        List<TestCaseDTO> listTest = testCaseService.findByProblemIdAndExample(problemDTO.getId(),"check");
        List<String> program = problemDTO.getLanguage();
        UserEntity user = securityUtils.getUser(request);
        List<SubmissionEntity> list = submissionRepository.findByProblem_idAndUser_id(problemDTO.getId(),user.getId());
        List<SubmissionDTO> listSub = new ArrayList<>();
        for(SubmissionEntity submissionEntity : list){
            listSub.add(submissionDTOConverter.toSubmissionDTO(submissionEntity));
        }
        mav.addObject("listSub", ReverseList.reverse(listSub));
        mav.addObject("detail", problemDTO);
        mav.addObject("listTest", listTest);
        mav.addObject("listLanguage", LanguageUtils.listLanguage(program));
        return mav;
    }
}
