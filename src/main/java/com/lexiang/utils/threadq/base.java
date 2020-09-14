package com.lexiang.utils.threadq;

/**
 * 线程六种状态
 * 1、新建状态 NEW
 * 2、运行状态 RUNNABLE
 * 3、阻塞 BLOCKED
 * 4、等待 WAITING
 * 5、超时等待 TIME_WAITING
 * 6、终止 TERMINATED
 */
public class base {

    private static volatile String aaa = "12345";

    static class Thread1 implements  Runnable{

        @Override
        public void run() {
            while (true){
                try {
                    if(Thread.currentThread().isInterrupted()){
                        return;
                    }
                    System.out.println(aaa);
                    //  LockSupport.park();
                    //Thread.sleep(1000);
                    //System.out.println(Thread.currentThread().getName()+"正在运行中");
                }catch (Exception e){
                    System.out.println("休眠失败");
                }
            }
        }
    }

    static class Thread2 implements  Runnable{

        @Override
        public void run() {
            System.out.println(aaa);
        }
    }


    public static void main(String[] args) throws Exception {
        Thread1 run1 = new base.Thread1();
        run1.notify();
        Thread thread1 = new Thread(run1, "线程1");
        System.out.println(thread1.getState());
        thread1.start();
        aaa = "dffff";
        while (true){
            Thread.sleep(1000);
            System.out.println(thread1.getState());
        }

        /*System.out.println("主线程运行");
        Thread.sleep(10000);
        System.out.println("朱线程运行2");
        thread1.interrupt();
        Thread.State*/

    }


}
