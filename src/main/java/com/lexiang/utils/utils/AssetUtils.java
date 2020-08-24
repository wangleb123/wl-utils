package com.lexiang.utils.utils;

import com.lexiang.utils.enums.CodeEnum;
import com.lexiang.utils.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AssetUtils {

    public static int assert_none = 1;
    public static int assert_list_none = 2;
    public static int assert_no_none = 3;




    public static BusinessException exceptionThrow(CodeEnum errorCode){
        throw  new BusinessException(errorCode.getCode(),errorCode.getName());
    }


    public  static void  assertObject(Object date,Integer type,CodeEnum errorCode){
         if(type == AssetUtils.assert_none){
             if (date == null){
                 log.info(errorCode.getName());
                 throw  exceptionThrow(errorCode);
             }
         }
         if(type == AssetUtils.assert_no_none){
             if(date != null){
                 log.info(errorCode.getName());
                 throw  exceptionThrow(errorCode);
             }
         }
    }

    public static void main(String[] args) {
        Object aa = null;

        System.out.println(aa.toString());
    }
}
