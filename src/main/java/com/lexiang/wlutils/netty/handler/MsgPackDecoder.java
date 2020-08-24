package com.lexiang.wlutils.netty.handler;

import com.lexiang.wlutils.netty.codec.MessagePackCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;

/**
 * messagePack解码
 */
public class MsgPackDecoder extends MessageToMessageDecoder<ByteBuf> {
    /**
     *
     * @param channelHandlerContext 上下文
     * @param byteBuf 编码后的数据
     * @param list 解码数据存放数组
     */
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) {
        list.add(MessagePackCodec.ByteBufDecoder(byteBuf));
    }
}