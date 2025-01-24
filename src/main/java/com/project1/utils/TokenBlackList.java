package com.project1.utils;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TokenBlackList {
    private final Set<String> blacklist = new HashSet<String>();
    public void addToken(String token) {
        blacklist.add(token);
    }
    public boolean isBlacklisted(String token) {
        return blacklist.contains(token);
    }
}
