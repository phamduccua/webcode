package com.project1.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContestInstallDTO {
    private Long contestId;
    private boolean show_test;
    private List<String> languages;
}
