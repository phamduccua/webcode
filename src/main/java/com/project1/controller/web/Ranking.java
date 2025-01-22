package com.project1.controller.web;

import com.project1.converter.UserConverter;
import com.project1.entity.enums.group;
import com.project1.model.dto.UserDTO;
import com.project1.model.request.RankingRequest;
import com.project1.model.response.RankingResponse;
import com.project1.service.RankingService;
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
public class Ranking {
    @Autowired
    private RankingService rankingService;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private SecurityUtils securityUtils;
    @Autowired
    private GroupUtils groupUtils;
    @GetMapping("/admin/ranking")
    public ModelAndView ranking(@ModelAttribute RankingRequest ranking, HttpServletRequest request, HttpSession session) {
        ModelAndView mav = new ModelAndView("admin/problem/ranking");
        UserDTO user = userConverter.toUserDTO(securityUtils.getUser(request));
        Map<String,String> listgroup = groupUtils.group(user.getClass_id(), group.type());
        mav.addObject("modelRanking", ranking);
        String groups = request.getParameter("group");
        if (groups != null && !groups.isEmpty()) {
            session.setAttribute("group", groups);
            ranking.setGroup(groups);
        } else {
            groups = (String) session.getAttribute("group");
            if (groups != null) {
                ranking.setGroup(groups);
            }
            else{
                ranking.setGroup(listgroup.keySet().iterator().next());
            }
        }
        if(!groupUtils.isInGroup(user.getClass_id(),ranking.getGroup())){
            ModelAndView mavtmp = new ModelAndView("index");
            return mavtmp;
        }
        List<RankingResponse> listranking = rankingService.findAllRanking(ranking, PageRequest.of(ranking.getPage() - 1,ranking.getMaxPageItems()));
        ranking.setListResult(listranking);
        ranking.setTotalItems(rankingService.countTotalItem(ranking));
        if(ranking.getTotalItems() % ranking.getMaxPageItems() == 0){
            ranking.setTotalPage(ranking.getTotalItems() / ranking.getMaxPageItems());
        }
        else{
            ranking.setTotalPage(ranking.getTotalItems() / ranking.getMaxPageItems() + 1);
        }
        mav.addObject("listranking", listranking);
        mav.addObject("listGroup", listgroup);
        return mav;
    }
}
