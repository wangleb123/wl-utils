package com.lexiang.utils.thread;

public class ThreadUtils {

    public static Thread getThread(){
       return Thread.currentThread();
    }

    public static void waits(Object lock){
        try {
            lock.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
