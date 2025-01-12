package com.project1.service.impl;

import com.project1.entity.ProblemEntity;
import com.project1.repository.ProblemRepository;
import com.project1.service.DeleteProblemService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DeleteProblemServiceImpl implements DeleteProblemService {
    @Autowired
    private ProblemRepository problemRepository;
    @Override
    public void deleteProblem(Long problemId) {
        ProblemEntity problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new RuntimeException("Problem not found"));
        problem.getContestEntites().forEach(contest -> contest.getProblemEntities().remove(problem));
        problemRepository.delete(problem);
    }
}
