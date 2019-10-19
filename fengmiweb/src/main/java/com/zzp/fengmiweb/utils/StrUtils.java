package com.zzp.fengmiweb.utils;

public class StrUtils {
    public static boolean isEmpty(String ...msg){
        for (String s : msg) {
            if (s==null || s.length()<=0){
                return true;
            }
        }
        return false;
    }
}
