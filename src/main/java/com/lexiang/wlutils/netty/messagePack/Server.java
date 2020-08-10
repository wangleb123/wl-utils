package com.lexiang.wlutils.netty.messagePack;

import com.lexiang.wlutils.netty.dilution.BootstrapDo;
import com.lexiang.wlutils.netty.dilution.ChannelDo;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetAddress;


public class Server {

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap
               .group(bossGroup,workerGroup)
               .channel(NioServerSocketChannel.class)
               .option(ChannelOption.SO_BACKLOG,100)
               .childHandler(new serverChannelHandler());

        BootstrapDo.ServerCatchDeal(bootstrap,9999,bossGroup,workerGroup);

    }

    static class serverChannelHandler extends ChannelInitializer<SocketChannel>{
        @Override
        protected void initChannel(SocketChannel socketChannel){
            ChannelDo
                    .init(socketChannel,new businessHandler())
                    .codec();
        }
    }


    static class businessHandler extends SimpleChannelInboundHandler<ByteBuf>{

        @Override
        protected void messageReceived(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {

        }
    }

}
