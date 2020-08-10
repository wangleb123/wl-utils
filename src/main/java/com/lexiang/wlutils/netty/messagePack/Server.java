package com.lexiang.wlutils.netty.messagePack;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.apache.commons.collections4.BagUtils;
import org.msgpack.MessagePack;

import java.util.List;

public class Server {

    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
       try {
           ServerBootstrap bootstrap = new ServerBootstrap();
           bootstrap
                   .group(bossGroup,workerGroup)
                   .channel(NioServerSocketChannel.class)
                   .option(ChannelOption.SO_BACKLOG,100)
                   .childHandler(new serverChannelHandler());

           ChannelFuture f = bootstrap.bind(9999).sync();
           f.channel().closeFuture().sync();

       }catch (Exception e){
           e.printStackTrace();
       }finally {
           bossGroup.shutdownGracefully();
           workerGroup.shutdownGracefully();
       }
    }

    static class serverChannelHandler extends ChannelInitializer<SocketChannel>{
        @Override
        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline()
                    .addLast(new businessHandler())
                    .addLast(new MsgPackDecoder())
                    .addLast(new MsgPackEncode());
        }
    }

    static class MsgPackEncode extends MessageToByteEncoder<Object> {
        @Override
        protected void encode(ChannelHandlerContext channelHandlerContext, Object o, ByteBuf byteBuf) throws Exception {
            MessagePack messagePack = new MessagePack();
            //serialize 编码
            byte[] data = messagePack.write(o);
            byteBuf.writeBytes(data);

        }
    }
    static class MsgPackDecoder extends MessageToMessageDecoder<ByteBuf>{

        @Override
        protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

            final byte[] array;
           final int len = byteBuf.readableBytes();
           array = new byte[len];
           byteBuf.getBytes(byteBuf.readerIndex(),array,0,len);
           MessagePack messagePack = new MessagePack();
           list.add(messagePack.read(array));
        }
    }

    static class businessHandler extends ChannelHandlerAdapter{
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            super.channelRead(ctx, msg);
        }
    }

}
