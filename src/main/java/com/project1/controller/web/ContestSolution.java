package com.project1.controller.web;

import com.project1.converter.SubmissionDTOConverter;
import com.project1.entity.ContestEntity;
import com.project1.entity.SubmissionEntity;
import com.project1.entity.UserEntity;
import com.project1.model.dto.ProblemDTO;
import com.project1.model.dto.SubmissionDTO;
import com.project1.model.dto.TestCaseDTO;
import com.project1.repository.ContestRepository;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class ContestSolution {
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
    @Autowired
    private ContestRepository contestRepository;
    @GetMapping("api/contest/{id}/solution/{code}")
    public ModelAndView cotestSolution(@PathVariable Long id,@PathVariable String code, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("web/contest_solution");
        LocalDateTime now = LocalDateTime.now();
        ContestEntity contest = contestRepository.findContestById(id);
        if(now.isAfter(contest.getStartTime()) && now.isBefore(contest.getEndTime())){
            ProblemDTO problemDTO = findProblemService.findByCode(code);
            List<TestCaseDTO> listTest = testCaseService.findByProblemIdAndExample(problemDTO.getId(),"check");
            List<String> program = problemDTO.getLanguage();
            UserEntity user = securityUtils.getUser(request);
            List<SubmissionEntity> list = submissionRepository.findByProblem_idAndUser_id(problemDTO.getId(),user.getId());
            List<SubmissionDTO> listSub = new ArrayList<>();
            for(SubmissionEntity submissionEntity : list){
                SubmissionDTO submissionDTO = submissionDTOConverter.toSubmissionDTO(submissionEntity);
                submissionDTO.setContestId(id);
                listSub.add(submissionDTO);
            }
            mav.addObject("id",id);
            mav.addObject("listSub",listSub);
            mav.addObject("detail", problemDTO);
            mav.addObject("listTest", listTest);
            mav.addObject("listLanguage", LanguageUtils.listLanguage(program));
            return mav;
        }
        else{
            ModelAndView tmp = new ModelAndView("web/not_found");
            return tmp;
        }
    }
}
