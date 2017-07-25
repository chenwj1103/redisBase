package com.base.dao;

/**
 * Created by Chen Weijie on 2017/7/23.
 */
public class RedisListTest extends RedisClient {




//    list-compress-depth 0
    public static void test1() {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            double d = Math.random();
            int num = (int) (d * 100000000);

            String listKey = "listKey:" + num;

            for (int j = 0; j < 8001; j++) {
                num++;
                String value = "test key length ,need length is gt 64 byte.it is too large ll" + num;
                lPush(listKey,value);
            }
            System.out.println("listKey:" + listKey);
        }

        long end = System.currentTimeMillis();
        System.out.println("time==========" + (end - start) / 1000);

    }


    //    list-compress-depth 1
    public static void test2() {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            double d = Math.random();
            int num = (int) (d * 100000000);

            String listKey = "listKey:" + num;

            for (int j = 0; j < 8001; j++) {
                num++;
                String value = "test key length ,need length is gt 64 byte.it is too large ll" + num;
                lPush(listKey,value);
            }
            System.out.println("listKey:" + listKey);
        }

        long end = System.currentTimeMillis();
        System.out.println("time==========" + (end - start) / 1000);

    }







    public static void main(String[] args) {
        test1();
    }


}
