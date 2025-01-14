package com.project1.model.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class ContestDTO {
    private Long id;
    private String name;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private Long created_by;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime end_time;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime start_time;
    private int status;
    private List<String> language;
}
