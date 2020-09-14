package com.lexiang.utils.threadq;

import com.lexiang.utils.thread.SleepUtils;
import com.lexiang.utils.thread.ThreadUtils;

import java.util.concurrent.TimeUnit;

public class DeadLock {

    static class Thread1 implements Runnable{

        private final Object lockA;

        private final Object lockB;

        public Thread1(Object lockA,Object lockB){
            this.lockA = lockA;
            this.lockB = lockB;
        }


        @Override
        public void run() {
            synchronized (lockA){
                System.out.println(ThreadUtils.getThread().getName() +"获取A锁");
                SleepUtils.sleep(TimeUnit.SECONDS,3);
                synchronized (lockB){

                }
            }
        }
    }

    static class Thread2 implements Runnable{

        private final Object lockA;
        private final Object lockB;

        public Thread2(Object lockA,Object lockB){
            this.lockA = lockA;
            this.lockB = lockB;
        }


        @Override
        public void run() {
            synchronized (lockB){
                System.out.println(ThreadUtils.getThread().getName() +"获取B锁");
                synchronized (lockA){

                }
            }
        }
    }


    public static void main(String[] args) {
        Object lockA = new Object();
        Object lockB = new Object();

        Thread thread1 = new Thread(new Thread1(lockA, lockB), "线程1");
        Thread thread2 = new Thread(new Thread2(lockA, lockB), "线程2");
        thread1.start();
        thread2.start();
        thread1.interrupt();

    }



}
