package com.lexiang.wlutils.netty.dilution;

import io.netty.channel.Channel;

import java.util.concurrent.TimeUnit;



public class EvenLoopDo {

    /**
     * eventLoop task定时任务
     * @param channel 通道
     * @param runnable 处理业务
     * @param timeUnit 时间单位
     * @param nums 时间数量
     */
    public static void schedule(Channel channel, Runnable runnable, TimeUnit timeUnit, long nums){
        channel.eventLoop().schedule(runnable,nums,timeUnit);
    }

}
