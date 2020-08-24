package com.lexiang.wlutils.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class NettyHttpResponse {




    /**
     * 消息处理失败 发送一个失败请求 应答客户端
     * @param context 处理上下文
     * @param request 请求
     * @param status http响应类型
     */
    public static void sendError(ChannelHandlerContext context, FullHttpRequest request, HttpResponseStatus status) {
        DefaultFullHttpResponse defaultFullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status);
        if (defaultFullHttpResponse.status().code() != HttpResponseStatus.OK.code()) {
            ByteBuf buf = Unpooled.copiedBuffer(defaultFullHttpResponse.status().toString(), CharsetUtil.UTF_8);
            defaultFullHttpResponse.content().writeBytes(buf);
            buf.release();
        }
        // 如果长连接好存在 关闭长连接
        boolean keepLive = HttpHeaderUtil.isKeepAlive(request);
        ChannelFuture future = context.channel().writeAndFlush(request);
        if (!keepLive) {
            future.addListener(ChannelFutureListener.CLOSE);
        }

    }



}
