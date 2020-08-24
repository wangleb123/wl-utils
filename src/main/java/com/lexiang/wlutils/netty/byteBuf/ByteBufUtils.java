package com.lexiang.wlutils.netty.byteBuf;

import io.netty.buffer.ByteBuf;

public class ByteBufUtils {

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
