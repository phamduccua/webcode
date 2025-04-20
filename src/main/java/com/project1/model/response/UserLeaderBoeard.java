package com.project1.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserLeaderBoeard {
    private String fullname;
    private List<String> status;
    private Integer count;
}
