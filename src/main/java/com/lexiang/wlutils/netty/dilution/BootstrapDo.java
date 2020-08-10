package com.lexiang.wlutils.netty.dilution;

import io.netty.bootstrap.AbstractBootstrap;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import java.net.SocketAddress;

public class BootstrapDo {

    public static void ServerCatchDeal(AbstractBootstrap bootstrap,int port, EventLoopGroup...eventLoopGroup){

        try {
            ChannelFuture f = bootstrap.bind(port).sync();
            f.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            for (EventLoopGroup nioEventLoopGroup : eventLoopGroup) {
                nioEventLoopGroup.shutdownGracefully();
            }
        }
    }

    public static void clientCatchDeal(AbstractBootstrap bootstrap,String ip,int port, EventLoopGroup...eventLoopGroup){

        try {
            ChannelFuture f = bootstrap.bind(ip,port).sync();
            f.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            for (EventLoopGroup nioEventLoopGroup : eventLoopGroup) {
                nioEventLoopGroup.shutdownGracefully();
            }
        }
    }

}
