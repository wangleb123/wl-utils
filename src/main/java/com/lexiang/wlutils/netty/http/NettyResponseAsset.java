package com.lexiang.wlutils.netty.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;

public class NettyResponseAsset {


    public static boolean decode(FullHttpRequest request){
        return !request.decoderResult().isSuccess();
    }

    public static boolean method(FullHttpRequest request, HttpMethod ...method){
        boolean flag = false;
        for (HttpMethod httpMethod : method) {
            if(request.method().equals(httpMethod)){
                flag = true;
                break;
            }
        }
        return !flag;
    }





}
