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
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        list.add(MessagePackCodec.ByteBufDecoder(byteBuf));
    }
}