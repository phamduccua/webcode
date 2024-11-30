package com.project1.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class SubmissionDTO {
    private Long id;
    private String problemName;
    private int status;
    private double executionTime;
    private Long memoryUsed;
    private String language;
    private Date submittedAt;
    private String problemCode;
    public String getFormattedDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(submittedAt);
    }

    public String getFormattedTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        return timeFormat.format(submittedAt);
    }
}
