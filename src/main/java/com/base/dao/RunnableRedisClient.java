package com.base.dao;

import java.util.Random;

/**
 * Created by Chen Weijie on 2017/7/23.
 */
public class RunnableRedisClient extends RedisClient implements Runnable {

    private String threadName;

    RunnableRedisClient(String threadName) {
        this.threadName = threadName;
        System.out.println("Creating " + threadName);
    }

    public void run() {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000000; i++) {
            double d =Math.random();
            int num = (int)(d*100000000);

            for (int j=0 ;j<100;j++){
                num++;
                String StringKey = "num:" + num;
                set(StringKey, num+"");
                System.out.println("StringKey======="+StringKey);
            }

            for (int j=0 ;j<100;j++){
                num++;
                String listKey ="listKey:"+num;
                lPush(listKey,num+"");
                System.out.println("listKey======="+listKey);
            }

            for (int j=0 ;j<100;j++){
                num++;
                String mapKey ="mapKey:"+num;
                hSet(mapKey,"test",num+"");
                System.out.println("mapKey======="+mapKey);
            }


            for (int j=0 ;j<100;j++){
                num++;
                String setKey ="setKey:"+num;
                sAdd(setKey,num+"");
                System.out.println("setKey======="+setKey);
            }


            for (int j=0 ;j<100;j++){
                num++;
                String zaddKey ="zaddKey:"+num;
                zAdd(zaddKey,Math.random(),num+"");
                System.out.println("zaddKey======="+zaddKey);
            }


        }
        long end = System.currentTimeMillis();

        System.out.println("time==========" + (end - start) / 1000);

    }


    public void start() {
        System.out.println("Starting " + threadName);

        Thread thread = new Thread(this, threadName);
        thread.start();

    }


    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }
}
