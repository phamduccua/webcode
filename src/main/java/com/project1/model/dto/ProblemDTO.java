package com.project1.model.dto;

import com.project1.entity.ProgramingLanguageEntity;

import java.util.List;

public class ProblemDTO extends AbstractDTO{
    private String code;
    private String title;
    private String description;
    private String type;
    private String topic;
    private String difficulty;
    private String inputFormat;
    private String outputFormat;
    private String example;
    private String group;
    private String constraints;
    private String color;
    private float time_limit;
    private Long memory_limit;
    private List<String> language;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getInputFormat() {
        return inputFormat;
    }

    public void setInputFormat(String inputFormat) {
        this.inputFormat = inputFormat;
    }

    public String getOutputFormat() {
        return outputFormat;
    }

    public void setOutputFormat(String outputFormat) {
        this.outputFormat = outputFormat;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getConstraints() {
        return constraints;
    }

    public void setConstraints(String constraints) {
        this.constraints = constraints;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getTime_limit() {
        return time_limit;
    }

    public void setTime_limit(float time_limit) {
        this.time_limit = time_limit;
    }

    public Long getMemory_limit() {
        return memory_limit;
    }

    public void setMemory_limit(Long memory_limit) {
        this.memory_limit = memory_limit;
    }

    public List<String> getLanguage() {
        return language;
    }

    public void setLanguage(List<String> language) {
        this.language = language;
    }
}
