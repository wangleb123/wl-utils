package com.lexiang.wlutils.netty.handler;

import com.lexiang.wlutils.codec.messagepack.Codec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;


/**
 * messagePack编码
 */
public class MsgPackEncode  extends MessageToByteEncoder<Object> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Object arg1, ByteBuf arg2) throws Exception {
        Codec.ByteBufEncoder(arg1,arg2);
    }
}
