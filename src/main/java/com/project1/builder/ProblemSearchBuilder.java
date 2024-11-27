package com.project1.builder;

import java.util.List;

public class ProblemSearchBuilder {
    private String code;
    private String title;
    private List<String> topic;
    private Long group;

    private ProblemSearchBuilder(Builder builer){
        this.code = builer.code;
        this.title = builer.title;
        this.topic = builer.topic;
        this.group = builer.group;
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

    public static class Builder{
        private String code;
        private String title;
        private List<String> topic;
        private Long group;

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
        public ProblemSearchBuilder build(){
            return new ProblemSearchBuilder(this);
        }
    }
}
