package com.lexiang.utils.utils;


import org.apache.commons.lang3.StringUtils;


/**
 * String 操作
 */
public class WLStringUtils {

    /**
     * 字符串判空（包含null 和 " "）
     * @param data 传入的字符串
     * @return 是否为空
     */
    public static boolean isEmptyTrim(String data){
        if(!StringUtils.isEmpty(data)){
            String trim = data.trim();
            if(StringUtils.isEmpty(trim)){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param data
     * @return
     */
    public static boolean isEmptyNull(String data){
        return null == data;
    }

    /**
     * 字符串判空
     * @param data 传入的字符串
     * @return 是否为空
     */
    public static boolean isEmpty(String data){
        return !StringUtils.isEmpty(data);
    }



    public static void main(String[] args) {

        System.out.println(WLStringUtils.isEmptyTrim("   "));
    }
}
