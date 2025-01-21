package com.project1.controller.admin;

import com.project1.converter.SubmissionDTOConverter;
import com.project1.converter.UserConverter;
import com.project1.entity.ProblemEntity;
import com.project1.entity.SubmissionEntity;
import com.project1.entity.UserEntity;
import com.project1.entity.enums.difficulty;
import com.project1.entity.enums.group;
import com.project1.entity.enums.language;
import com.project1.model.dto.ProblemDTO;
import com.project1.model.dto.SubmissionDTO;
import com.project1.model.dto.TestCaseDTO;
import com.project1.model.dto.UserDTO;
import com.project1.model.request.ProblemSearchRequest;
import com.project1.model.request.RankingRequest;
import com.project1.model.request.SubmissionRequest;
import com.project1.model.response.ProblemSearchReponse;
import com.project1.model.response.RankingResponse;
import com.project1.model.response.StatusResponse;
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
    private GetSubmission getSubmission;
    @Autowired
    private SubmissionRepository submissionRepository;
    @Autowired
    private SubmissionDTOConverter submissionDTOConverter;
    @Autowired
    private SecurityUtils securityUtils;
    @Autowired
    private RankingService rankingService;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private GroupUtils groupUtils;
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
    public ModelAndView history(@ModelAttribute SubmissionRequest submission,HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/problem/history");
        mav.addObject("submission", submission);
        List<SubmissionDTO> listSub = getSubmission.getSub(request,PageRequest.of(submission.getPage() - 1,submission.getMaxPageItems()));
        submission.setListResult(listSub);
        submission.setTotalItems(getSubmission.countItems(request));
        if(submission.getTotalItems() % submission.getMaxPageItems() == 0){
            submission.setTotalPage(submission.getTotalItems() / submission.getMaxPageItems());
        }
        else{
            submission.setTotalPage(submission.getTotalItems() / submission.getMaxPageItems() + 1);
        }
        mav.addObject("listSub", listSub);
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

    @GetMapping("/admin/status")
    public ModelAndView status(@ModelAttribute SubmissionRequest submission,HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/problem/list_status");
        mav.addObject("submission", submission);
        List<StatusResponse> listSub = getSubmission.getAll(PageRequest.of(submission.getPage() - 1,submission.getMaxPageItems()));
        submission.setListResult(listSub);
        submission.setTotalItems(getSubmission.countItems(request));
        if(submission.getTotalItems() % submission.getMaxPageItems() == 0){
            submission.setTotalPage(submission.getTotalItems() / submission.getMaxPageItems());
        }
        else{
            submission.setTotalPage(submission.getTotalItems() / submission.getMaxPageItems() + 1);
        }
        mav.addObject("listSub", listSub);
        return mav;
    }
    @GetMapping("/admin/ranking")
    public ModelAndView ranking(@ModelAttribute RankingRequest ranking, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/problem/ranking");
        UserDTO user = userConverter.toUserDTO(securityUtils.getUser(request));
        mav.addObject("modelRanking", ranking);
        List<RankingResponse> listranking = rankingService.findAllRanking(ranking,PageRequest.of(ranking.getPage() - 1,ranking.getMaxPageItems()));
        ranking.setListResult(listranking);
        ranking.setTotalItems(rankingService.countTotalItem(ranking));
        if(ranking.getTotalItems() % ranking.getMaxPageItems() == 0){
            ranking.setTotalPage(ranking.getTotalItems() / ranking.getMaxPageItems());
        }
        else{
            ranking.setTotalPage(ranking.getTotalItems() / ranking.getMaxPageItems() + 1);
        }
        Map<String,String> listgroup = groupUtils.group(user.getClass_id(),group.type());
        mav.addObject("listranking", listranking);
        mav.addObject("listGroup", listgroup);
        return mav;
    }
}
