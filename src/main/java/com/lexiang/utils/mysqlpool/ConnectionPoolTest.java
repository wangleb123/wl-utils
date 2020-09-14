package com.lexiang.utils.mysqlpool;

import java.awt.*;
import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class ConnectionPoolTest {

   static ConnectionPool pool =   new ConnectionPool(10);


   static CountDownLatch start = new CountDownLatch(1);

   static class ConnectionRunner implements Runnable{

       int count;

       AtomicInteger got;

       AtomicInteger notGot;

       public ConnectionRunner(int count ,AtomicInteger got , AtomicInteger notGot){
           this.count = count;
           this.got = got;
           this.notGot = notGot;
       }

       @Override
       public void run() {
           try {
               start.await();
           }catch (Exception ex){

           }
           while (count > 0){
               try {
                   Connection connection = pool.fetchConnection(1000);
                   if(connection != null){
                       try {
                           connection.createStatement();
                           connection.commit();
                       }finally {
                           pool.releaseConnection(connection);
                           got.incrementAndGet();
                       }

                   }
               }catch (Exception e){

               }finally {
                   count--;
               }
           }
       }
   }

}
