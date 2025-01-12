package com.project1.model.dto;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
public class ProblemContestDTO {
    private Long id;
    private String title;
    private String description;
    private String difficulty = "cap_1";
    private String inputFormat;
    private String constraints;
    private String outputFormat;
    private String code;
    private String type = "CONTEST";
    private String topic = "CONTEST";
    private Long classId = 0L;
    private float time_limit;
    private Long memory_limit;
    private Long id_contest;
    private List<String> language;
}
