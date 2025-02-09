package com.project1.controller.admin;

import com.project1.converter.SubmissionDTOConverter;
import com.project1.converter.UserConverter;
import com.project1.entity.ProblemEntity;
import com.project1.entity.SubmissionEntity;
import com.project1.entity.enums.difficulty;
import com.project1.entity.enums.group;
import com.project1.entity.enums.language;
import com.project1.model.dto.ProblemDTO;
import com.project1.model.dto.SubmissionDTO;
import com.project1.model.dto.TestCaseDTO;
import com.project1.model.dto.UserDTO;
import com.project1.model.request.ProblemSearchRequest;
import com.project1.model.request.SubmissionRequest;
import com.project1.model.response.ProblemSearchReponse;
import com.project1.model.response.StatusResponse;
import com.project1.repository.ProblemRepository;
import com.project1.repository.SubmissionRepository;
import com.project1.service.*;
import com.project1.utils.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private SubmissionRepository submissionRepository;
    @Autowired
    private SubmissionDTOConverter submissionDTOConverter;
    @Autowired
    private SecurityUtils securityUtils;
    @Autowired
    private GroupUtils groupUtils;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private GetSubmission getSubmission;
    @Autowired
    private ProblemRepository problemRepository;
    @GetMapping("admin/list")
    public ModelAndView problemList(@ModelAttribute ProblemSearchRequest problemSearchRequest , HttpServletRequest request, HttpSession session) {
        ModelAndView mav = new ModelAndView("admin/problem/list");
        UserDTO user = userConverter.toUserDTO(securityUtils.getUser(request));
        Map<String,String> listgroup = groupUtils.group(user.getClass_id(), group.type());
        String groups = request.getParameter("group");
        if (groups != null && !groups.isEmpty()) {
            session.setAttribute("group", groups);
            problemSearchRequest.setGroup(groups);
        } else {
             groups = (String) session.getAttribute("group");
            if (groups != null) {
                problemSearchRequest.setGroup(groups);
            }
            else{
                problemSearchRequest.setGroup(listgroup.keySet().iterator().next());
            }
        }
        if((problemSearchRequest.getTopic() != null &&
                !problemSearchRequest.getTopic().isEmpty()) &&
                (problemSearchRequest.getTopic().get(0).equals(" ") ||
                        problemSearchRequest.getTopic().get(0).equals(""))){
            problemSearchRequest.setTopic(null);
            session.setAttribute("topic", null);
        }
        List<String> topics = problemSearchRequest.getTopic();
        if(topics != null && !topics.isEmpty()) {
            session.setAttribute("topic", topics);
            problemSearchRequest.setTopic(topics);
        }
        else{
            topics = (List<String>) session.getAttribute("topic");
            if(topics != null && !topics.isEmpty()) {
                problemSearchRequest.setTopic(topics);
            }
        }
        if(!groupUtils.isInGroup(user.getClass_id(),problemSearchRequest.getGroup())){
            ModelAndView mavtmp = new ModelAndView("index");
            return mavtmp;
        }
        List<ProblemSearchReponse> list = problemSerachService.findAll(problemSearchRequest,request, PageRequest.of(problemSearchRequest.getPage() - 1,problemSearchRequest.getMaxPageItems()));
        List<String> listTopic = topicService.findTopic(ClassIdUtils.toClassId(problemSearchRequest.getGroup()));
        problemSearchRequest.setListResult(list);
        problemSearchRequest.setTotalItems(problemSerachService.countTotalItems(problemSearchRequest));
        if(problemSearchRequest.getPage() > problemSearchRequest.getTotalItems()){
            problemSearchRequest.setPage(1);
        }
        if(problemSearchRequest.getTotalItems() % problemSearchRequest.getMaxPageItems() == 0){
            problemSearchRequest.setTotalPage(problemSearchRequest.getTotalItems() / problemSearchRequest.getMaxPageItems());
        }
        else{
            problemSearchRequest.setTotalPage(problemSearchRequest.getTotalItems() / problemSearchRequest.getMaxPageItems() + 1);
        }
        mav.addObject("modelSearch", problemSearchRequest);
        mav.addObject("problemList", list);
        mav.addObject("listGroup", listgroup);
        mav.addObject("listTopic", listTopic);
        return mav;
    }
    @GetMapping("admin/add")
    public ModelAndView problemEdit(@ModelAttribute("problemAdd") ProblemDTO problemDTO , HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/problem/add");
        mav.addObject("problemAdd", problemDTO);
        mav.addObject("listGroup", group.type());
        mav.addObject("listDifficulty", difficulty.type());
        mav.addObject("listLanguages", language.type());
        return mav;
    }

    @GetMapping("admin/detail-{code}")
    public ModelAndView problemEit(@PathVariable("code") String code , HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/problem/edit-detail");
        mav.addObject("code",code);
        ProblemDTO problemDTO = editProblemService.findByCode(code);
        mav.addObject("nameProblem", problemDTO.getTitle());
        mav.addObject("problemEdit", problemDTO);
        mav.addObject("listGroup", group.type());
        mav.addObject("listDifficulty", difficulty.type());
        mav.addObject("listLanguages", language.type());
        return mav;
    }

    @GetMapping("admin/list-testcase-{code}")
    public ModelAndView problemAddTest(@PathVariable("code") String code , HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/problem/list-testcase");
        mav.addObject("code",code);
        ProblemDTO problemDTO = editProblemService.findByCode(code);
        List<TestCaseDTO> testCaseDTO = testCaseService.findByProblemId(problemDTO.getId());
        mav.addObject("problemCode",problemDTO.getCode());
        mav.addObject("nameProblem", problemDTO.getTitle());
        mav.addObject("listTest",testCaseDTO);
        return mav;
    }

    @GetMapping("/admin/submission/{id}/edit")
    public ModelAndView problemEdit(@PathVariable("id") Long id , HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/problem/edit_submission");
        SubmissionEntity submissionEntity = submissionRepository.findById(id).get();
        SubmissionDTO submission = submissionDTOConverter.toSubmissionDTO(submissionEntity);
        ProblemDTO problemDTO = findProblemService.findByCode(submissionEntity.getProblem().getCode());
        mav.addObject("problemDTO", problemDTO);
        mav.addObject("submission", submission);
        mav.addObject("listLanguage", LanguageUtils.listLanguage(problemDTO.getLanguage()));
        return mav;
    }

    @GetMapping("admin/exercises")
    public ModelAndView problemListByUser(@ModelAttribute ProblemSearchRequest problemSearchRequest , HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/problem/list_problem_by_user");
        UserDTO user = userConverter.toUserDTO(securityUtils.getUser(request));
        problemSearchRequest.setCreatedBy(user.getId());
        problemSearchRequest.setGroup(null);
        List<ProblemSearchReponse> list = problemSerachService.findAll(problemSearchRequest,request, PageRequest.of(problemSearchRequest.getPage() - 1,problemSearchRequest.getMaxPageItems()));
        List<String> listTopic = topicService.findTopic(ClassIdUtils.toClassId(problemSearchRequest.getGroup()));
        problemSearchRequest.setListResult(list);
        problemSearchRequest.setTotalItems(problemSerachService.countTotalItems(problemSearchRequest));
        if(problemSearchRequest.getTotalItems() % problemSearchRequest.getMaxPageItems() == 0){
            problemSearchRequest.setTotalPage(problemSearchRequest.getTotalItems() / problemSearchRequest.getMaxPageItems());
        }
        else{
            problemSearchRequest.setTotalPage(problemSearchRequest.getTotalItems() / problemSearchRequest.getMaxPageItems() + 1);
        }
        mav.addObject("modelSearch", problemSearchRequest);
        mav.addObject("problemList", list);
        mav.addObject("listTopic", listTopic);
        return mav;
    }

    @GetMapping("admin/submission/problem/{code}")
    public ModelAndView problemEdit(@ModelAttribute SubmissionRequest submission, @PathVariable("code") String code , HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/problem/submission_problem");
        mav.addObject("submission", submission);
        ProblemEntity problem = problemRepository.findByCode(code);
        List<StatusResponse> listSub = getSubmission.getByProblem(problem.getId() ,PageRequest.of(submission.getPage() - 1,submission.getMaxPageItems()));
        submission.setListResult(listSub);
        submission.setTotalItems(getSubmission.countByProbem(problem.getId()));
        if(submission.getTotalItems() % submission.getMaxPageItems() == 0){
            submission.setTotalPage(submission.getTotalItems() / submission.getMaxPageItems());
        }
        else{
            submission.setTotalPage(submission.getTotalItems() / submission.getMaxPageItems() + 1);
        }
        mav.addObject("problem", problem);
        mav.addObject("listSub", listSub);
        return mav;
    }
}
