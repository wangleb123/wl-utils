package com.lexiang.wlutils.netty.websocket.tranfer;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;

public interface NettyWebSocket {
    void handsShaker(ChannelHandlerContext ctx, FullHttpRequest request);

    void handsShakerClose(WebSocketServerHandshaker handsShaker, Channel channel, Object webSocketFrame);
}
