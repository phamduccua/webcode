package com.project1.entity.enums;

import java.util.LinkedHashMap;
import java.util.Map;

public enum group {
    NGON_NGU_LAP_TRINH_C("Ngôn ngữ lập trình C"),
    NGON_NGU_LAP_TRINH_CPP("Ngôn ngữ lập trình C++"),
    CAU_TRUC_DU_LIEU_VA_GIAI_THUAT("Cấu trúc giữ liệu và giải thuật"),
    NGON_NGU_LAP_TRINH_JAVA("Ngôn ngữ lập trình Java"),
    NGON_NGU_LAP_TRINH_PYTHON("Ngôn ngữ lập trình Python");

    private final String groupName;
    group(String groupName) {
        this.groupName = groupName;
    }
    public static Map<String,String> type(){
        Map<String,String> groups = new LinkedHashMap<String,String>();
        for(group it : group.values()){
            groups.put(it.toString(),it.groupName);
        }
        return groups;
    }
}
