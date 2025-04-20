package com.project1.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RankingResponse {
    private String userName;
    private String fullName;
    private Long countAccept;
    private Long countTried;
}
