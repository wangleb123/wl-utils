package com.lexiang.wlutils.netty.http;

import com.alibaba.fastjson.JSON;
import com.lexiang.wlutils.netty.byteBuf.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.HashMap;
import java.util.Map;

/**
 * netty的request工具对象
 * @author wangle
 */
public class NettyHttpRequest {

    /**
     * 获取post参数
     * @param request 请求
     * @return 参数
     */
    public static String postsParam(FullHttpRequest request){
        ByteBuf content = request.content();
        byte[] bytes = ByteBufUtils.getBytes(content);
        return new String(bytes, 0, bytes.length);
    }

    /**
     *
     * @param request 请求
     * @param clazz 对象class
     * @param <T> 转换的对象
     * @return request中的参数转换的对象
     */
    public static <T> T postsParam(FullHttpRequest request,Class<T> clazz){
        ByteBuf content = request.content();
        byte[] bytes = ByteBufUtils.getBytes(content);
        String s = new String(bytes, 0, bytes.length);
        return JSON.parseObject(s, clazz);
    }

    /**
     * 获取get参数
     * @param request 请求
     * @return 参数
     */
    public static Map<String, Object>  getsParam(FullHttpRequest request){
        Map<String, Object> paramMap = new HashMap<>();

        QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
        decoder.parameters().forEach((key, value) -> paramMap.put(key, value.get(0)));
        return paramMap;
    }



}
