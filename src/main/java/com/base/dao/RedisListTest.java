package com.base.dao;

/**
 * Created by Chen Weijie on 2017/7/23.
 */
public class RedisListTest extends RedisClient {




//    list-max-ziplist-entries >512 (520)
//    list-max-ziplist-value >64 (70)
    public static void test1() {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            double d = Math.random();
            int num = (int) (d * 100000000);

            String listKey = "listKey:" + num;

            for (int j = 0; j < 520; j++) {
                num++;
                String value = "test key length ,need length is gt 64 byte.it is too large ll" + num;
                lPush(listKey,value);
            }
            System.out.println("listKey:" + listKey);
        }

        long end = System.currentTimeMillis();
        System.out.println("time==========" + (end - start) / 1000);

    }



//    list-max-ziplist-entries >512(520)
//    list-max-ziplist-value < 64 (60)
    public static void test2() {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            double d = Math.random();
            int num = (int) (d * 100000000);

            String listKey = "listKey:" + num;

            for (int j = 0; j < 520; j++) {
                num++;
                String value = "test key length ,need length is gt 64 byte.it is to" + num;
                lPush(listKey,value);
            }
            System.out.println("listKey:" + listKey);
        }

        long end = System.currentTimeMillis();
        System.out.println("time==========" + (end - start) / 1000);

    }


    //    list-max-ziplist-entries <512(500)
//    list-max-ziplist-value > 64 (70)
    public static void test3() {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            double d = Math.random();
            int num = (int) (d * 100000000);

            String listKey = "listKey:" + num;

            for (int j = 0; j < 500; j++) {
                num++;
                String value = "test key length ,need length is gt 64 byte.it is too large ll" + num;
                lPush(listKey,value);
            }
            System.out.println("listKey:" + listKey);
        }

        long end = System.currentTimeMillis();
        System.out.println("time==========" + (end - start) / 1000);

    }




    //    list-max-ziplist-entries <512(500)
//    list-max-ziplist-value < 64 (60)
    public static void test4() {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            double d = Math.random();
            int num = (int) (d * 100000000);

            String listKey = "listKey:" + num;

            for (int j = 0; j < 500; j++) {
                num++;
                String value = "test key length ,need length is gt 64 byte.it is to" + num;
                lPush(listKey,value);
            }
            System.out.println("listKey:" + listKey);
        }

        long end = System.currentTimeMillis();
        System.out.println("time==========" + (end - start) / 1000);

    }



    public static void main(String[] args) {
//        test2();
    }


}
