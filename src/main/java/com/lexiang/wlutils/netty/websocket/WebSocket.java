package com.lexiang.wlutils.netty.websocket;

import com.lexiang.wlutils.netty.dilution.BootstrapDo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

import java.net.InetSocketAddress;

public class WebSocket {

    /**
     * http协议升级
     * @param context 上下文
     * @param request 请求
     * @param transfer nettyWebSocket
     */
    public static void argumentSub(ChannelHandlerContext context, Object request, com.lexiang.wlutils.netty.websocket.tranfer.NettyWebSocket transfer){
        if(request instanceof FullHttpRequest){
            transfer.handsShaker(context,(FullHttpRequest)request);
        }else if(request instanceof WebSocketFrame){
            context.fireChannelRead(((WebSocketFrame) request).retain());
        }
    }

}
