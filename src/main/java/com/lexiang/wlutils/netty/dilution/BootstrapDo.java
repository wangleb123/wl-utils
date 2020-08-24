package com.lexiang.wlutils.netty.dilution;

import com.lexiang.wlutils.netty.websocket.tranfer.AbstractNettyWebSocket;
import io.netty.bootstrap.AbstractBootstrap;
import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class BootstrapDo {

    private static final Log logger = LogFactory.getLog(BootstrapDo.class);

    public static final int CLIENT = 1;

    public static final int SERVER = 2;


    /**
     * bootStrap初始化，捕捉错误等等
     * @param type socket类型
     * @param bootstrap netty起始对象
     * @param address 地址信息
     * @param eventLoopGroup reactor线程组
     */
    public static void catchDeal(int type,AbstractBootstrap<?,?> bootstrap, InetSocketAddress address, EventLoopGroup ...eventLoopGroup){

        try {
            Long beginTime = System.currentTimeMillis();
            ChannelFuture f = null;
            //绑定address地址
            if (type == CLIENT) {
                Bootstrap bootstrap1 = (Bootstrap) bootstrap;
                f = bootstrap1.connect(address).sync();
            } else if(type == SERVER) {
                f = bootstrap.bind(address).sync();
            }
            //预关闭
            assert f != null;
            f.channel().closeFuture().sync();
            Long endTime = System.currentTimeMillis();
            logger.info("服务器启动完成，耗时:"+(endTime - beginTime)+"毫秒,已经在端口：["+address.getPort()+"]进行阻塞等待");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //轮训关闭线程池，防止资源占用
            for (EventLoopGroup nioEventLoopGroup : eventLoopGroup) {
                nioEventLoopGroup.shutdownGracefully();
            }
        }
    }


    /**
     * 获取reactor组，负责channel与服务端的链接
     * 数量默认为主机的core*2
     * @return EventLoopGroup
     */
    public static EventLoopGroup getBossGroup(){
        return new NioEventLoopGroup();
    }

    /**
     * 获取reactor组，负责socketChannel的读写操作
     * @return EventLoopGroup
     */
    public static EventLoopGroup getWorkerGroup(){
        return new NioEventLoopGroup();
    }


    public static EventLoopGroup getWorkerGroup(int threadNum){
        return new NioEventLoopGroup(threadNum);
    }


}
