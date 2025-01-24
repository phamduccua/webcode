package com.project1.controller.web;

import com.project1.model.dto.SubmissionDTO;
import com.project1.model.request.SubmissionRequest;
import com.project1.service.GetSubmission;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class ContestHistory {
    @Autowired
    private GetSubmission getSubmission;
    @GetMapping("api/contest/{id}/history")
    public ModelAndView contestHistory(@PathVariable Long id, @ModelAttribute SubmissionRequest submission, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("web/contest_history");
        mav.addObject("submission", submission);
        List<SubmissionDTO> listSub = getSubmission.listSubmissionContest(request,id, PageRequest.of(submission.getPage() - 1,submission.getMaxPageItems()));
        submission.setListResult(listSub);
        submission.setTotalItems(getSubmission.countItemsContest(request,id));
        if(submission.getTotalItems() % submission.getMaxPageItems() == 0){
            submission.setTotalPage(submission.getTotalItems() / submission.getMaxPageItems());
        }
        else{
            submission.setTotalPage(submission.getTotalItems() / submission.getMaxPageItems() + 1);
        }
        mav.addObject("id",id);
        mav.addObject("listSub", listSub);
        return mav;
    }
}
