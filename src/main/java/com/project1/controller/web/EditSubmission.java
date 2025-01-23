package com.project1.controller.web;

import com.project1.converter.SubmissionDTOConverter;
import com.project1.entity.SubmissionEntity;
import com.project1.model.dto.ProblemDTO;
import com.project1.model.dto.SubmissionDTO;
import com.project1.repository.SubmissionRepository;
import com.project1.service.FindProblemService;
import com.project1.utils.LanguageUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class EditSubmission {
    @Autowired
    private SubmissionRepository submissionRepository;
    @Autowired
    private SubmissionDTOConverter submissionDTOConverter;
    @Autowired
    private FindProblemService findProblemService;
    @GetMapping("/api/submission/{id}/edit")
    public ModelAndView problemEdit(@PathVariable("id") Long id , HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("web/editsubmission");
        SubmissionEntity submissionEntity = submissionRepository.findById(id).get();
        SubmissionDTO submission = submissionDTOConverter.toSubmissionDTO(submissionEntity);
        ProblemDTO problemDTO = findProblemService.findByCode(submissionEntity.getProblem().getCode());
        mav.addObject("problemDTO", problemDTO);
        mav.addObject("submission", submission);
        mav.addObject("listLanguage", LanguageUtils.listLanguage(problemDTO.getLanguage()));
        return mav;
    }
}
