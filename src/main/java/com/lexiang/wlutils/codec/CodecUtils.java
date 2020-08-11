package com.lexiang.wlutils.codec;

import io.netty.buffer.ByteBuf;
import org.msgpack.MessagePack;
import org.msgpack.type.Value;

import java.io.IOException;

public class CodecUtils {

    private static final String BYTE_BUF = "byteBuf";

    private static final String STRING = "string";

    //获取byteBuf的字节数组
    public static byte[] getBytes(ByteBuf byteBuf){

        final byte[] array;
        int i = byteBuf.readableBytes();
        array = new byte[i];
        byteBuf.readBytes(array,0,i);
        return array;
    }

}
