package com.project1.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContestUpdateLanguageDTO {
    private Long contestId;
    private List<String> languages;
}
