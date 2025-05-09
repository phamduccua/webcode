package com.project1.controller.admin;

import com.project1.model.request.SubmissionRequest;
import com.project1.model.response.StatusResponse;
import com.project1.service.GetSubmission;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
@RequestMapping("/")
public class AdminStatus {
    @Autowired
    private GetSubmission getSubmission;
    @GetMapping("/admin/status")
    public ModelAndView status(@ModelAttribute SubmissionRequest submission, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("admin/problem/list_status");
        mav.addObject("submission", submission);
        List<StatusResponse> listSub = getSubmission.getAll(PageRequest.of(submission.getPage() - 1,submission.getMaxPageItems()));
        submission.setListResult(listSub);
        submission.setTotalItems(getSubmission.countAll());
        if(submission.getTotalItems() % submission.getMaxPageItems() == 0){
            submission.setTotalPage(submission.getTotalItems() / submission.getMaxPageItems());
        }
        else{
            submission.setTotalPage(submission.getTotalItems() / submission.getMaxPageItems() + 1);
        }
        mav.addObject("listSub", listSub);
        return mav;
    }
}
