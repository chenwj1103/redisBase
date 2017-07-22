package com.base.dao;

import java.util.Random;

/**
 * Created by Chen Weijie on 2017/7/23.
 */
public class RedisExample extends RedisClient {


    public static void test() {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 5000000; i++) {
            Random random = new Random(100000000);
            int num = random.nextInt();
            String key = "num:" + num;
            set(key, num+"");
            System.out.println("key======="+key);
        }
        long end = System.currentTimeMillis();

        System.out.println("time==========" + (end - start) / 1000);

    }

    public static void main(String[] args) {
        test();
    }

}
