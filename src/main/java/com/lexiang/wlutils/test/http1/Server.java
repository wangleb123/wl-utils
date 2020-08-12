package com.lexiang.wlutils.test.http1;

import com.lexiang.wlutils.netty.dilution.BootstrapDo;
import com.lexiang.wlutils.netty.dilution.HandlerDo;
import com.lexiang.wlutils.netty.http.NettyHttpRequest;
import com.lexiang.wlutils.netty.http.NettyHttpResponse;
import com.lexiang.wlutils.netty.http.NettyResponseAsset;
import com.lexiang.wlutils.test.User;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;


public class Server {

    public static void main(String[] args) {

        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup bossGroup = BootstrapDo.getBossGroup();
        EventLoopGroup workerGroup = BootstrapDo.getWorkerGroup();

        bootstrap
               .group(bossGroup,workerGroup)
               .channel(NioServerSocketChannel.class)
               .option(ChannelOption.SO_BACKLOG,100)
               .childHandler(new serverChannelHandler());

        BootstrapDo.catchDeal(BootstrapDo.SERVER,bootstrap,new InetSocketAddress(9999),bossGroup,workerGroup);

    }

    static class serverChannelHandler extends ChannelInitializer<SocketChannel>{
        @Override
        protected void initChannel(SocketChannel socketChannel){
            HandlerDo
                    .init(socketChannel)
                    .HttpCodec()
                    .httpAggregator()
                    .business(new businessHandler());
        }
    }


    static class businessHandler extends SimpleChannelInboundHandler<FullHttpRequest>{
        @Override
        protected void messageReceived(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
            if(NettyResponseAsset.decode(req)){
                NettyHttpResponse.sendError(ctx,HttpResponseStatus.BAD_REQUEST);
                return;
            }
            if(NettyResponseAsset.method(req,HttpMethod.GET,HttpMethod.POST)){
               NettyHttpResponse.sendError(ctx,HttpResponseStatus.METHOD_NOT_ALLOWED);
               return;
            }

            User user1 = NettyHttpRequest.postsParam(req, User.class);
            DefaultHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK,Unpooled.copiedBuffer("<html><head><p style='color:red'>"+req.method()+req.uri()+"</p></head><body>", CharsetUtil.UTF_8));
            response.headers().set(HttpHeaderNames.CONTENT_TYPE,"image/jepg");
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        }
    }

}
