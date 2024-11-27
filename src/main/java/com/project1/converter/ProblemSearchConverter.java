package com.project1.converter;

import com.project1.entity.ProblemEntity;
import com.project1.entity.enums.difficulty;
import com.project1.model.response.ProblemSearchReponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class ProblemSearchConverter {
    @Autowired
    private ModelMapper modelMapper;
    public ProblemSearchReponse toProblemSearchReponse(ProblemEntity problemEntity) {
        ProblemSearchReponse problemSearchReponse = modelMapper.map(problemEntity, ProblemSearchReponse.class);
        problemSearchReponse.setDifficul(difficulty.type().get(problemEntity.getDifficulty()));
        return problemSearchReponse;
    }
}
