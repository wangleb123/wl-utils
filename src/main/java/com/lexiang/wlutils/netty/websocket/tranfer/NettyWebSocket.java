package com.lexiang.wlutils.netty.websocket.tranfer;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public interface NettyWebSocket {
    void handsShaker(ChannelHandlerContext ctx, FullHttpRequest request);
}
