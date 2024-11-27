package com.project1.entity.enums;

import java.util.TreeMap;

public enum difficulty {
    cap_1(1),
    cap_2(2),
    cap_3(3),
    cap_4(4),
    cap_5(5),
    cap_6(6);

    private final int difficultyName;
    difficulty(int difficultyName) {
        this.difficultyName = difficultyName;
    }

    public static TreeMap<String,Integer> type(){
        TreeMap<String,Integer> difficultys = new TreeMap<>();
        for(difficulty item : difficulty.values()){
            difficultys.put(item.toString(),item.difficultyName);
        }
        return difficultys;
    }
}
