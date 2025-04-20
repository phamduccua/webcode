package com.project1.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ContestCreate {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String name;
    private Long createdBy;
}
