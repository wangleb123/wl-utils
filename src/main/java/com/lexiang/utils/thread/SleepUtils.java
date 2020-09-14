package com.lexiang.utils.thread;

import java.util.concurrent.TimeUnit;

public class SleepUtils {

    /**
     *
     * @param timeUnit 时间单位
     * @param num 时间长度
     */
    public static void sleep(TimeUnit timeUnit,long num){
        try {
            timeUnit.sleep(num);
        }catch (InterruptedException e){
            System.out.println("线程睡眠中不可中断L");
        }

    }

}
