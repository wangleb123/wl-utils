package com.lexiang.wlutils.netty.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

public class WebSocket {

    /**
     * http协议升级
     */
    public static void argumentSub(ChannelHandlerContext context, Object request, com.lexiang.wlutils.netty.websocket.tranfer.NettyWebSocket transfer){
        if(request instanceof FullHttpRequest){
            transfer.handsShaker(context,(FullHttpRequest)request);
        }else if(request instanceof WebSocketFrame){
            context.fireChannelRead(((WebSocketFrame) request).retain());
        }
    }


}
