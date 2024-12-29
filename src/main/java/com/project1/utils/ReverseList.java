package com.project1.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReverseList {
    public static  <T> List<T> reverse(List<T> list) {
        Collections.reverse(list);
        return list;
    }
}
