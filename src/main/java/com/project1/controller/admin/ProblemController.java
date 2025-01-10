package com.project1.controller.admin;

import com.project1.converter.SubmissionDTOConverter;
import com.project1.entity.SubmissionEntity;
import com.project1.entity.enums.difficulty;
import com.project1.entity.enums.group;
import com.project1.entity.enums.language;
import com.project1.model.dto.ProblemDTO;
import com.project1.model.dto.SubmissionDTO;
import com.project1.model.dto.TestCaseDTO;
import com.project1.model.request.ProblemSearchRequest;
import com.project1.model.response.ProblemSearchReponse;
import com.project1.repository.SubmissionRepository;
import com.project1.service.*;

import com.project1.utils.ClassIdUtils;
import com.project1.utils.LanguageUtils;
import com.project1.utils.ReverseList;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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
    @Autowired
    private GetSubmission getSubmission;
    @Autowired
    private SubmissionRepository submissionRepository;
    @Autowired
    private SubmissionDTOConverter submissionDTOConverter;
    @GetMapping("admin/list")
    public ModelAndView problemList(@ModelAttribute ProblemSearchRequest problemSearchRequest , HttpServletRequest request, HttpSession session) {
        ModelAndView mav = new ModelAndView("admin/problem/list");
        String groups = request.getParameter("group");
        if (groups != null && !groups.isEmpty()) {
            session.setAttribute("group", groups);
            problemSearchRequest.setGroup(groups);
        } else {
             groups = (String) session.getAttribute("group");
            if (groups != null) {
                problemSearchRequest.setGroup(groups);
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
        List<ProblemSearchReponse> list = problemSerachService.findAll(problemSearchRequest, PageRequest.of(problemSearchRequest.getPage() - 1,problemSearchRequest.getMaxPageItems()));
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
        mav.addObject("listGroup", group.type());
        mav.addObject("listTopic", listTopic);
        return mav;
    }

    @GetMapping("admin/history")
    public ModelAndView problemHistory(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/problem/history");
        List<SubmissionDTO> listSub = getSubmission.getSub();
        mav.addObject("listSub", ReverseList.reverse(listSub));
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

    @GetMapping("admin/detail-{id}")
    public ModelAndView problemEit(@PathVariable("id") Long id , HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/problem/edit-detail");
        mav.addObject("id",id);
        ProblemDTO problemDTO = editProblemService.findById(id);
        mav.addObject("nameProblem", problemDTO.getTitle());
        mav.addObject("problemEdit", problemDTO);
        mav.addObject("listGroup", group.type());
        mav.addObject("listDifficulty", difficulty.type());
        mav.addObject("listLanguages", language.type());
        return mav;
    }

    @GetMapping("admin/list-testcase-{id}")
    public ModelAndView problemAddTest(@PathVariable("id") Long id , HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/problem/edit-testcase");
        mav.addObject("id",id);
        ProblemDTO problemDTO = editProblemService.findById(id);
        List<TestCaseDTO> testCaseDTO = testCaseService.findByProblemId(id);
        mav.addObject("nameProblem", problemDTO.getTitle());
        mav.addObject("listTest",testCaseDTO);
        return mav;
    }

    @GetMapping("admin/assignment-{code}")
    public ModelAndView assignment(@PathVariable("code") String code , HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/problem/assignment");
        ProblemDTO problemDTO = findProblemService.findByCode(code);
        List<TestCaseDTO> listTest = testCaseService.findByProblemIdAndExample(problemDTO.getId(),"check");
        List<String> program = problemDTO.getLanguage();
        List<SubmissionEntity> list = submissionRepository.findByProblem_id(problemDTO.getId());
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
