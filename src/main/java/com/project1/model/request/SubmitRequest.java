package com.project1.model.request;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class SubmitRequest {
    private final String clientId = "1f4d3fcbba851100ec6759746014014d";
    private final String clientSecret = "426d518970afa735e841635546e82e1efd1bb52c1eefa65bc7d13f84391ca91c";
    private String script;
    private String stdin;
    private String language;
    private int versionIndex;
}
