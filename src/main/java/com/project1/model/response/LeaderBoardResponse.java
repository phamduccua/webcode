package com.project1.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class LeaderBoardResponse {
    private List<String> name_problem;
    private List<UserLeaderBoeard> user;
}
