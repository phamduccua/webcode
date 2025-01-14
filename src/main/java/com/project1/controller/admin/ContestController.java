package com.project1.controller.admin;
import com.project1.model.dto.ContestDTO;
import com.project1.model.dto.ProblemDTO;
import com.project1.model.dto.TestCaseDTO;
import com.project1.model.dto.UserDTO;
import com.project1.model.response.UserResponse;
import com.project1.service.ContestService;
import com.project1.service.EditProblemService;
import com.project1.service.IUserService;
import com.project1.service.TestCaseService;
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
    @Autowired
    private TestCaseService testCaseService;
    @Autowired
    private IUserService userSerice;
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
    @GetMapping("/admin/problem_contest-edit-{code}")
    public ModelAndView editProblemContest(@PathVariable String code){
        ModelAndView mav = new ModelAndView("admin/contest/problem_contest_edit");
        ProblemDTO problemDTO = editProblemService.findByCode(code);
        mav.addObject("nameProblem", problemDTO.getTitle());
        mav.addObject("problemDTO",problemDTO);
        return mav;
    }

    @GetMapping("/admin/list_testcase_contest-problem-{code}")
    public ModelAndView listTestcaseContestProblem(@PathVariable String code){
        ModelAndView mav = new ModelAndView("admin/contest/list-testcase");
        mav.addObject("code",code);
        ProblemDTO problemDTO = editProblemService.findByCode(code);
        List<TestCaseDTO> testCaseDTO = testCaseService.findByProblemId(problemDTO.getId());
        mav.addObject("problemCode",problemDTO.getCode());
        mav.addObject("nameProblem", problemDTO.getTitle());
        mav.addObject("listTest",testCaseDTO);
        return mav;
    }

    @GetMapping("/admin/list-member-{id}")
    public ModelAndView listMemberContest(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("admin/contest/list_member");
        List<UserResponse> list_member = userSerice.findByRole("USER",id);
        mav.addObject("id",id);
        mav.addObject("list_member",list_member);
        return mav;
    }
}
