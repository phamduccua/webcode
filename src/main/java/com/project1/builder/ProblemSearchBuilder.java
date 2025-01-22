package com.project1.builder;

import java.util.List;

public class ProblemSearchBuilder {
    private String code;
    private String title;
    private List<String> topic;
    private Long group;
    private Long createdBy;

    private ProblemSearchBuilder(Builder builder){
        this.code = builder.code;
        this.title = builder.title;
        this.topic = builder.topic;
        this.group = builder.group;
        this.createdBy = builder.createdBy;
    }


    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getTopic() {
        return topic;
    }

    public Long getGroup() {
        return group;
    }

    public Long getCreatedBy() {return createdBy;}

    public static class Builder{
        private String code;
        private String title;
        private List<String> topic;
        private Long group;
        private Long createdBy;

        public Builder setCode(String code) {
            this.code = code;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setTopic(List<String> topic) {
            this.topic = topic;
            return this;
        }
        public Builder setGroup(Long group) {
            this.group = group;
            return this;
        }

        public Builder setCreatedBy(Long createdBy) {
            this.createdBy = createdBy;
            return this;
        }
        public ProblemSearchBuilder build(){
            return new ProblemSearchBuilder(this);
        }
    }
}
