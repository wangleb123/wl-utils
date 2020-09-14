package com.lexiang.utils.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.cglib.beans.BeanMap;

import java.util.Map;

public class JsonUtils {

    /**
     *
     * @param map 传入map
     * @param clazz 传入class类型
     * @param <T> 范型
     * @return 传入class类型对应的对象实例
     */
    public static <T> T MapToObject(Map<String,Object> map, Class<T> clazz){
        if(map != null){
            return JSON.parseObject(JSON.toJSONString(map),clazz);
        }
        return null;
    }

    public static  <T> T ObjectToBean(Object object,Class<T> clazz){
        if(object != null){
            return JSONObject.parseObject(JSONObject.toJSONString(object), clazz);
        }
        return null;
    }

    /**
     * 将对象装换为map
     *
     * @param bean
     * @return
     */
    public static <T> Map<String, T> beanToMap(Object bean) {
        Map<String ,T> map = JSON.parseObject(JSON.toJSONString(bean, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteMapNullValue), Map.class);
        return map;
    }
}
