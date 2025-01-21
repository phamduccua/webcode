package com.project1.utils;

import org.springframework.stereotype.Component;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Component
public class GroupUtils {
    public Map<String,String> group(List<String> list,Map<String,String> map){
        Map<String,String> group = new LinkedHashMap<>();
        for(String item : list){
            if(map.containsKey(item)){
                group.put(item,map.get(item));
            }
        }
        return group;
    }
}
