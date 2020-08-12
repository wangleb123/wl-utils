package com.lexiang.wlutils.netty.dilution;

import com.lexiang.wlutils.netty.handler.MsgPackDecoder;
import com.lexiang.wlutils.netty.handler.MsgPackEncode;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;


/**
 * @author wangle
 * ChannelDo处理器
 */
public class HandlerDo {

    //存储本地的ChannelInit，防止服务端初始化和客户端初始化造成 ChannelPipeline 出错
    private  static final ThreadLocal<Channel>  channelPipeline = new ThreadLocal<>();

    /**
     *
     * @param channel channel
     * @return ChannelDo处理器
     */
    public static HandlerDo init(Channel channel){
        channelPipeline.set(channel);
        return  new HandlerDo();
    }


    /**
     * 开启pack解码
     */
    public HandlerDo packCodec(){
        channelPipeline.get()
                .pipeline()
                .addLast(new MsgPackDecoder())
                .addLast(new MsgPackEncode());
        return this;
    }

    /**
     *
     * @param channelHandler channelHandler 处理器数组（业务相关）
     * @return
     */
    public HandlerDo business(ChannelHandler ...channelHandler){
        channelPipeline.get()
                .pipeline()
                .addLast(channelHandler);
        return  new HandlerDo();
    }

    /**
     * 开启http编解码
     */
    public HandlerDo HttpCodec(){
        channelPipeline.get()
                .pipeline()
                .addLast(new HttpServerCodec());
        return this;
    }

    /**
     * 开启http消息聚合器,防止http编解码遗漏（无参数）
     */
    public HandlerDo httpAggregator(){

        HttpObjectAggregator httpObjectAggregator = new HttpObjectAggregator(1024 * 512);
        channelPipeline.get()
                .pipeline()
                .addLast("httpAggregator",httpObjectAggregator);
        return this;
    }

    /**
     * 开启http消息聚合器,防止http编解码遗漏
     * @param arg1 参数1
     * @param arg2 参数2
     */
    public HandlerDo httpAggregator(Integer arg1, Integer arg2){
        HttpObjectAggregator httpObjectAggregator = new HttpObjectAggregator(arg1 * arg2);
        channelPipeline.get()
                .pipeline()
                .addLast("httpAggregator",httpObjectAggregator);
        return this;
    }

    /**
     * messagePack的防止粘包和半包处理
     */
    public HandlerDo StickyPackCodec(){

        channelPipeline.get()
                .pipeline()
                .addLast("base-line",new LengthFieldBasedFrameDecoder(65535,0,
                        2,0,2))
                .addLast("length-field",new LengthFieldPrepender(2));
        return this;
    }


    /**
     * webSocketHandler处理
     * @return
     */
    public HandlerDo webSocket(){
        channelPipeline.get()
                .pipeline()
                .addLast("http-codec", new HttpServerCodec())
                // HTTP头和body拼接成完整请求体
                .addLast("aggregator", new HttpObjectAggregator(65536))
                // 大文件传输策略
                .addLast("http-chunked", new ChunkedWriteHandler());
        return this;
    }



}
