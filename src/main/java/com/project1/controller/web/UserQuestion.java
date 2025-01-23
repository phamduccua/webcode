package com.project1.controller.web;

import com.project1.converter.UserConverter;
import com.project1.entity.enums.group;
import com.project1.model.dto.UserDTO;
import com.project1.model.request.ProblemSearchRequest;
import com.project1.model.response.ProblemSearchReponse;
import com.project1.service.ProblemSearchService;
import com.project1.service.TopicService;
import com.project1.utils.ClassIdUtils;
import com.project1.utils.GroupUtils;
import com.project1.utils.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class UserQuestion {
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private SecurityUtils securityUtils;
    @Autowired
    private GroupUtils groupUtils;
    @Autowired
    private ProblemSearchService problemSerachService;
    @Autowired
    private TopicService topicService;
    @GetMapping("api/question")
    public ModelAndView problemList(@ModelAttribute ProblemSearchRequest problemSearchRequest , HttpServletRequest request, HttpSession session) {
        ModelAndView mav = new ModelAndView("web/question");
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
            ModelAndView mavtmp = new ModelAndView("web/not_found");
            session.setAttribute("group", null);
            problemSearchRequest.setGroup(null);
            return mavtmp;
        }
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
        mav.addObject("listGroup", listgroup);
        mav.addObject("listTopic", listTopic);
        return mav;
    }
}
