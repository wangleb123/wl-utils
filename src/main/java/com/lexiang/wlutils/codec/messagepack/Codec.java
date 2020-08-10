package com.lexiang.wlutils.codec.messagepack;

import com.lexiang.wlutils.codec.CodecUtils;
import io.netty.buffer.ByteBuf;
import org.msgpack.MessagePack;
import org.msgpack.type.Value;


/**
 * messagePack编解码工具类
 */
public class Codec {


    /**
     * 编码
     * @param message message netty ByteBuf
     */
    public static void ByteBufEncoder(ByteBuf message){

        MessagePack messagePack = new MessagePack();
        try {
            messagePack.write(message);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 解码
     * @param message netty ByteBuf
     * @return messagePack对象
     */
    public static Value ByteBufDecoder(ByteBuf message){
        //将byteBuf转化为字节数组
        byte[] data = CodecUtils.getBytes(message);
        MessagePack messagePack = new MessagePack();
        try {
            Value read = messagePack.read(data);
            return read;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
}
