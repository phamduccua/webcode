package com.project1.service;
import com.project1.model.dto.ProblemDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface AddProblemService {
    void addProblem(ProblemDTO problemDTO, HttpServletRequest request);
}
