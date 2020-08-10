package com.lexiang.wlutils.netty.dilution;

import com.lexiang.wlutils.netty.handler.MsgPackDecoder;
import com.lexiang.wlutils.netty.handler.MsgPackEncode;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;

public class ChannelDo {

    //存储本地的ChannelInit，防止服务端初始化和客户端初始化造成 ChannelPipeline 出错
    private  static  ThreadLocal<ChannelPipeline>  channelPipeline;


    public static ChannelDo init(io.netty.channel.Channel channel, ChannelHandler ...channelHandler){
        channelPipeline.set(channel.pipeline().addLast(channelHandler));
       return  new ChannelDo();
    }


    /**
     * 开启pack解码
     */
    public void codec(){
        channelPipeline.get()
                .addLast(new MsgPackDecoder())
                .addLast(new MsgPackEncode());
    }

}
