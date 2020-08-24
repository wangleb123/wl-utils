package com.lexiang.utils.utils;


import org.apache.commons.lang3.StringUtils;

public class WLStringUtils {

    public static boolean isEmpty(String data){
        if(!StringUtils.isEmpty(data)){
            String trim = data.trim();
            if(StringUtils.isEmpty(trim)){
                return true;
            }
        }
        return false;
    }



    public static void main(String[] args) {

        System.out.println(WLStringUtils.isEmpty("   "));
    }
}
