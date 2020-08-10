package com.lexiang.wlutils.netty.messagePack;

import com.lexiang.wlutils.netty.dilution.BootstrapDo;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;

public class Client {

    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(bossGroup);
        BootstrapDo.clientCatchDeal(bootstrap,"127.0.0.1",9999,bossGroup);
    }


    static class clientChannelHandler extends ChannelInitializer<SocketChannel>{
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast();
        }
    }

    static class businessHandler extends SimpleChannelInboundHandler<ByteBuf>{
        @Override
        protected void messageReceived(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {

        }
    }

}
