package com.project1.model.request;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class SubmitRequest {
    private final String clientId = "f939428e5f4ad001de12ff6584a3efab";
    private final String clientSecret = "eaacb1d1637a9455ac8528e8cd28a6e3d5d5fe1a67899b3e533f9ae0fe17409";
    private String script;
    private String stdin;
    private String language;
    private int versionIndex;
}
