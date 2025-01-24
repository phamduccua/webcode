package com.project1.service;

import com.project1.model.dto.*;
import com.project1.model.request.ProblemByUserRequest;
import com.project1.model.response.LeaderBoardResponse;
import com.project1.model.response.ProblemByUserResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface ContestService {
    List<ContestDTO> findByUserCreateId(HttpServletRequest request);
    List<ContestDTO> findAll(HttpServletRequest request);
    void createContest(ContestCreate contestCreate);
    ContestDTO findContestById(Long id);
    void updateContest(ContestDTO contestDTO);
    void deleteContest(Long id);
    void addProblemContest(ProblemContestDTO problemContestDTO, HttpServletRequest request);
    List<ProblemDTO> findProblem(Long id);
    void updateProblemContest(ProblemContestDTO problemContestDTO,HttpServletRequest request);
    void editMember(Map<String,String> map);
    LeaderBoardResponse leaderBoard(ContestDTO contestDTO);
    void updateLanguage(ContestUpdateLanguageDTO contestUpdateLanguageDTO);
    List<ProblemByUserResponse> findByUser(ProblemByUserRequest problemByUserRequest, HttpServletRequest request, Pageable pageable);
    void updateProblemByUser(Map<String,String> map);
    void deleteProblemContest(Map<String,String> map);
    int countTotalProblemByUser(HttpServletRequest request);
}
