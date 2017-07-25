package com.base.dao;

/**
 * Created by Chen Weijie on 2017/7/23.
 */
public class RedisSetTest extends RedisClient {

//    set-max-intset-entries >512(520)
    public static void test() {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 200; i++) {
            double d = Math.random();
            int num = (int) (d * 100000000);

            String setKey = "setKey:" + num;

            for (int j = 0; j < 520; j++) {
                num++;
                sAdd(setKey,num+"t");
            }
            System.out.println("setKey=======" + setKey);
        }

        long end = System.currentTimeMillis();
        System.out.println("time==========" + (end - start) / 1000);

    }


    //    set-max-intset-entries <512(500)
    public static void test2() {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 200; i++) {
            double d = Math.random();
            int num = (int) (d * 100000000);

            String setKey = "setKey:" + num;

            for (int j = 0; j < 500; j++) {
                num++;
                sAdd(setKey,num+"t");
            }
            System.out.println("setKey=======" + setKey);
        }

        long end = System.currentTimeMillis();
        System.out.println("time==========" + (end - start) / 1000);

    }


    public static void main(String[] args) {
        test2();
//        test();
    }


}
