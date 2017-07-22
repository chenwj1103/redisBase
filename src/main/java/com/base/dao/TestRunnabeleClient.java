package com.base.dao;

/**
 * Created by Chen Weijie on 2017/7/23.
 */
public class TestRunnabeleClient {

    public static void main(String[] args) {


        RunnableRedisClient runnableRedisClient1 =new RunnableRedisClient("thread1");
        runnableRedisClient1.start();
        RunnableRedisClient runnableRedisClient2 =new RunnableRedisClient("thread2");
        runnableRedisClient2.start();



    }

}
