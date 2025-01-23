package com.project1.model.response;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
@Getter
@Setter
public class StatusResponse {
    private Long id;
    private String problemName;
    private String status = null;
    private String time;
    private Long memoryUsed;
    private String language;
    private Date submittedAt;
    private String problemCode;
    private String code;
    private String error;
    private String submitted;
    private String username;
    private String fullname;
    public String getFormattedDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(submittedAt);
    }

    public String getFormattedTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        return timeFormat.format(submittedAt);
    }
}
