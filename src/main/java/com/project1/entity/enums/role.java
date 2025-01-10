package com.project1.entity.enums;

import java.util.LinkedHashMap;

public enum role {
    ADMIN("ADMIN"),
    USER("USER");
    private final String name;
    role(String name) {
        this.name = name;
    }
    public static LinkedHashMap<String,String> getRole() {
        LinkedHashMap<String,String> roles = new LinkedHashMap<>();
        for(role x : role.values()){
            roles.put(x.toString(),x.name);
        }
        return roles;
    }
}
