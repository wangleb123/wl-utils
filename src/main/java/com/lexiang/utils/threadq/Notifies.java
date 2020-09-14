package com.lexiang.utils.threadq;

import com.lexiang.utils.thread.SleepUtils;
import com.lexiang.utils.thread.ThreadUtils;

import java.util.concurrent.TimeUnit;

public class Notifies{

    static class Wait implements Runnable{

        private final Object lockA;

        public Wait(Object lockA){
            this.lockA = lockA;
        }

        @Override
        public void run() {
            synchronized (lockA){
                System.out.println("线程1开始了");
                ThreadUtils.waits(lockA);
                System.out.println("线程1被唤醒了");
            }
        }
    }
    static class Notifiess implements Runnable{

        private final Object lockA;

        public Notifiess(Object lockA){
            this.lockA = lockA;
        }

        @Override
        public void run() {
            synchronized (lockA){
                System.out.println("开始唤醒线程");
                lockA.notify();
            }
        }
    }


    public static void main(String[] args) {

        Object o = new Object();
        Thread thread1 = new Thread(new Wait(o));
        Thread thread2 = new Thread(new Notifiess(o));
        thread1.start();
        SleepUtils.sleep(TimeUnit.SECONDS,5);
        thread2.start();
    }




}

