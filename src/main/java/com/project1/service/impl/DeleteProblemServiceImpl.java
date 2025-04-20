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

import java.io.File;
import java.util.StringTokenizer;

@Service
@Transactional
public class DeleteProblemServiceImpl implements DeleteProblemService {
    @Autowired
    private ProblemRepository problemRepository;
    private final String url = "D:/webcode/src/main/images/";
    @Override
    public void deleteProblem(Long problemId) {
        ProblemEntity problem = problemRepository.findById(problemId)
                .orElseThrow(() -> new RuntimeException("Problem not found"));
        String des = problem.getDescription();
        StringTokenizer st = new StringTokenizer(des, "\n");
        while(st.hasMoreTokens()) {
            String line = st.nextToken();
            if(line.contains("image")){
                File file = new File(url + line.substring(9,line.length() - 1));
                file.delete();
            }
        }
        problem.getContestEntites().forEach(contest -> contest.getProblemEntities().remove(problem));
        problemRepository.delete(problem);
    }
}
