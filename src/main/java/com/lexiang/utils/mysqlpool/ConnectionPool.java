package com.lexiang.utils.mysqlpool;

import org.springframework.util.ObjectUtils;

import java.sql.Connection;
import java.util.LinkedList;


public class ConnectionPool {



    static LinkedList<Connection> pool = new LinkedList<>();

    /**
     * 初始数据库化线程池大小
     * @param initialSize 线程池大小
     */
    public ConnectionPool(int initialSize){
        if(initialSize > 0){
            pool.addLast(ConnectionDriver.createConnection());
        }
    }


    /**
     * 用完链接之后还给数据库线程池
     * @param connection 链接
     */
    public void releaseConnection(Connection connection){
        if(connection != null){
            synchronized (pool){
                //释放后需要同志其他的消费者能感知到链接池中已经归还了一个线程
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    /**
     * 从数据库链接池获取连接
     * @param mills
     * @return
     * @throws InterruptedException
     */
    public Connection fetchConnection(long mills) throws InterruptedException{
        synchronized (pool){
            if(mills <= 0){
                while (pool.isEmpty()){
                    pool.wait();
                }
                return pool.removeFirst();
            }else {
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                while (pool.isEmpty() && remaining >0){
                    pool.wait(remaining);
                    remaining = future - System.currentTimeMillis();
                }
                Connection result = null;
                if(!pool.isEmpty()){
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }

}
