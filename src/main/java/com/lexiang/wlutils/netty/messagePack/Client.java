package com.lexiang.wlutils.netty.messagePack;

import com.alibaba.fastjson.JSON;
import com.lexiang.wlutils.netty.User;
import com.lexiang.wlutils.netty.dilution.HandlerDo;
import com.lexiang.wlutils.netty.handler.MsgPackDecoder;
import com.lexiang.wlutils.netty.handler.MsgPackEncode;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.util.CharsetUtil;
import org.apache.commons.codec.digest.XXHash32;

public class Client {

    public static void main(String[] args){
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap
                    .group(nioEventLoopGroup) //设置线程组
                    .channel(NioSocketChannel.class) //设置客户端通道实现类
                    .handler( new clientInitChannel());
            ChannelFuture sync = bootstrap.connect("127.0.0.1", 8888).sync();
            sync.channel().closeFuture().sync();
        }catch (Exception e){
            nioEventLoopGroup.shutdownGracefully();
        }
    }

    static class clientInitChannel extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            HandlerDo
                    .init(socketChannel)
                    .packCodec()
                    .StickyPackCodec()
                    .business(new clientChannel());
        }
    }
    static class clientChannel extends SimpleChannelInboundHandler<ByteBuf>{

        @Override
        protected void messageReceived(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
            System.out.println("服务器回复的消息："+byteBuf.toString(CharsetUtil.UTF_8));
            System.out.println("服务器回复的消息"+channelHandlerContext.channel().remoteAddress());
        }

        //当通道就绪时就会触发
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            User user = new User();
            user.setName("asdsad");
            user.setSex("asdasd");
            ctx.writeAndFlush(user);
        }
    }

}
