package com.project1.service;

import com.project1.entity.ContestEntity;
import com.project1.model.dto.ContestCreate;
import com.project1.model.dto.ContestDTO;
import com.project1.model.dto.ProblemContestDTO;
import com.project1.model.dto.ProblemDTO;

import java.util.List;

public interface ContestService {
    List<ContestDTO> findAll();
    void createContest(ContestCreate contestCreate);
    ContestDTO findContestById(Long id);
    void updateContest(ContestDTO contestDTO);
    void deleteContest(Long id);
    void addProblemContest(ProblemContestDTO problemContestDTO);
    List<ProblemDTO> findProblem(Long id);
    void updateProblemContest(ProblemContestDTO problemContestDTO);
}
