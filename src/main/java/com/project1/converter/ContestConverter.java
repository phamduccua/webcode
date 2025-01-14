package com.project1.converter;

import com.project1.entity.ContestEntity;
import com.project1.entity.UserEntity;
import com.project1.model.dto.ContestCreate;
import com.project1.model.dto.ContestDTO;
import com.project1.utils.SecurityUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Component

public class ContestConverter {
    @Autowired
    private ModelMapper modelMpper;
    public ContestDTO toContestDTO(ContestEntity contestEntity) {
        ContestDTO contestDTO = modelMpper.map(contestEntity, ContestDTO.class);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        contestDTO.setEnd_time(contestEntity.getEndTime());
        contestDTO.setStartDate(contestEntity.getStartTime().format(dateFormatter));
        contestDTO.setEndDate(contestEntity.getEndTime().format(dateFormatter));
        contestDTO.setStartTime(contestEntity.getStartTime().format(timeFormatter));
        contestDTO.setEndTime(contestEntity.getEndTime().format(timeFormatter));
        contestDTO.setStart_time(contestEntity.getStartTime());
        List<String> arr = new ArrayList<>();
        if(contestEntity.getLanguage() == null || contestEntity.getLanguage().equals("")) {
            System.out.println("null");
        }
        else{
            StringTokenizer str = new StringTokenizer(contestEntity.getLanguage(), ",");
            while(str.hasMoreTokens()){
                String language = str.nextToken();
                arr.add(language);
            }
        }
        contestDTO.setLanguage(arr);
        return contestDTO;
    }
    public ContestEntity toContestEntity(ContestCreate contestCreate) {
        ContestEntity contestEntity = modelMpper.map(contestCreate, ContestEntity.class);
        return contestEntity;
    }
}
