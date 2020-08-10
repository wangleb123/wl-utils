package com.lexiang.wlutils.netty.simple1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.websocketx.WebSocketClientProtocolHandler;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

public class Server {

    public static final String SERVER_INIT_OK = "服务器准备就绪";

    public static void main(String[] args) throws Exception{

        //创建两个线程组，两个都是无限循环，bossGroup为Nio的selector
        //创建boosGroup 负责链接请求
        //个数默认为cpu的核心数*2
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //创建workerGroup 负责真正与client的业务处理
        EventLoopGroup workerGroup = new NioEventLoopGroup(8);

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap
                    .group(bossGroup,workerGroup) //设置两个线程组
                    .channel(NioServerSocketChannel.class)  //设置服务器使用NioServerSocketChannel作为服务器的通道实现
                    .option(ChannelOption.SO_BACKLOG,128) //设置线程队列的到的线程个数
                    .childOption(ChannelOption.SO_KEEPALIVE,true) //设置爆出活动的链接状态
                    .childHandler(new serverInitHandler());  //给workerGroup的EventLoop对应的管道设置处理器
            System.out.println(SERVER_INIT_OK);

            //绑定端口并且同步运行
            //启动服务器
            ChannelFuture bind = bootstrap.bind(8888).sync();

            //关闭通道监听，不是立马关闭
            bind.channel().closeFuture().sync();
        }catch (Exception e){
            //关闭服务端
            bossGroup.shutdownGracefully();
        }

    }


    //创建通道初始化对象
    static class serverInitHandler extends ChannelInitializer<SocketChannel> {

        //给pipeLine设置处理器
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            //向管道中添加handler处理器
            socketChannel.pipeline().addLast(new serverHandler());
        }
    }


    //读取数据实例（这里我们可以读取客户端发送的消息）
    static class serverHandler extends SimpleChannelInboundHandler<ByteBuf>{

        /**
         *
         * @param channelHandlerContext 上下文文件，含有pipeLIne，channel，local地址
         * @param byteBuf 客户端发送的消息
         * @throws Exception
         */
        //服务器端接受客户端数据
        @Override
        protected void messageReceived(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
            System.out.println("客户端发送的消息为："+byteBuf.toString(CharsetUtil.UTF_8));
            System.out.println("客户端的地址为"+ channelHandlerContext.channel().remoteAddress());
        }

        //数据读取完毕
        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception{
            ctx.writeAndFlush(Unpooled.copiedBuffer("hello,客户端 喵～",CharsetUtil.UTF_8));
        }

        //处理异常，关闭通道
        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
            ctx.close();
        }
    }
}
