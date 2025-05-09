package com.project1.model.request;

import com.project1.model.dto.AbstractDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class ProblemSearchRequest extends AbstractDTO {
    private String codeOrtitle;
    private List<String> topic;
    private String group;
    private Long createdBy = null;

    public String getCodeOrtitle() {
        return codeOrtitle;
    }

    public void setCodeOrtitle(String codeOrtitle) {
        this.codeOrtitle = codeOrtitle;
    }

    public List<String> getTopic() {
        return topic;
    }

    public void setTopic(List<String> topic) {
        this.topic = topic;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    ProblemSearchRequest(){
        this.setMaxPageItems(100);
    }
}
