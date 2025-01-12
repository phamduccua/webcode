package com.project1.utils;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
@Component
public class RanddomCodeProblem {
    private final Set<String> generatedCodes = new HashSet<>();

    public String generateUniqueCode() {
        String code;
        do {
            code = generateRandomCode(18);
        } while (generatedCodes.contains(code));
        generatedCodes.add(code);
        return code;
    }

    private String generateRandomCode(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < length; i++) {
            code.append(chars.charAt(random.nextInt(chars.length())));
        }
        return code.toString();
    }
}
