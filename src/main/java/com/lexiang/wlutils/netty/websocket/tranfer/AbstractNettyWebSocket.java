package com.lexiang.wlutils.netty.websocket.tranfer;

import com.lexiang.wlutils.netty.http.NettyHttpResponse;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * http和webSocket相关操作
 * @author wangle
 *
 */
public class AbstractNettyWebSocket implements NettyWebSocket {

    private static final Log logger = LogFactory.getLog(AbstractNettyWebSocket.class);
    @Override
    public void handsShaker(ChannelHandlerContext context, FullHttpRequest request) {
        if (!request.decoderResult().isSuccess()) {
            NettyHttpResponse.sendError(context, request, HttpResponseStatus.BAD_REQUEST);
            return;
        }
        // 协议升级
        WebSocketServerHandshakerFactory factory = new WebSocketServerHandshakerFactory("ws:/" + context.channel() + "/websocket", null, false);
        WebSocketServerHandshaker handsShaker = factory.newHandshaker(request);
        // 存储握手信息
        if (handsShaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(context.channel());
        }else {
            // 表示握手成功
            handsShaker.handshake(context.channel(), request);
            logger.info("Http-websocket握手协议升级成功啦");
        }
    }

    /**
     * 关闭webSocket握手
     * @param handsShaker
     * @param channel
     * @param webSocketFrame
     */
    private void handsShakerClose(WebSocketServerHandshaker handsShaker,Channel channel, Object webSocketFrame){
        if (null == handsShaker) {
            sendMessage(channel,"该用户已经离线或者不存在该连接");
        }else {
            handsShaker.close(channel, ((CloseWebSocketFrame) webSocketFrame).retain());
        }
    }

    /**
     * 预关闭webSocket握手
     * @param handsShaker
     * @param channel
     * @param webSocketFrame
     */
    private void handsShakerCloseFuture(WebSocketServerHandshaker handsShaker,Channel channel, Object webSocketFrame){
        if (webSocketFrame instanceof CloseWebSocketFrame) {
          handsShakerClose(handsShaker,channel,webSocketFrame);
        }
    }
    /**
     * 传送text类型的websocket数据
     * @param channel 通道
     * @param message 消息文字
     *
     */
    public void sendMessage(Channel channel,String message){
        channel.writeAndFlush(new TextWebSocketFrame(message));
    }
}
