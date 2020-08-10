package com.lexiang.wlutils.netty.simple1;

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
import io.netty.util.CharsetUtil;

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
            socketChannel.pipeline().addLast(new clientChannel());
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
            ctx.writeAndFlush(Unpooled.copiedBuffer("hello server 喵～ ",CharsetUtil.UTF_8));

        }
    }

}
