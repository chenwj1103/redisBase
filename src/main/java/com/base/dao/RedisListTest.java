package com.base.dao;

/**
 * Created by Chen Weijie on 2017/7/23.
 */
public class RedisListTest extends RedisClient {




//    list-max-ziplist-size -2
//    # -2: max size: 8 Kb   <-- good (70*115=8050)
//    # -1: max size: 4 Kb   <-- good
//    list-compress-depth 0
    public static void test1() {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            double d = Math.random();
            int num = (int) (d * 100000000);

            String listKey = "listKey:" + num;

            for (int j = 0; j < 1000; j++) {
                num++;
                String value = "test key length ,need length is gt 64 byte.it is too large ll" + num;
                lPush(listKey,value);
            }
            System.out.println("listKey:" + listKey);
        }

        long end = System.currentTimeMillis();
        System.out.println("time==========" + (end - start) / 1000);

    }

//    list-max-ziplist-size -2
//    # -2: max size: 8 Kb   <-- good(70*110=7700)
//    # -1: max size: 4 Kb   <-- good
//    list-compress-depth 0
    public static void test2() {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            double d = Math.random();
            int num = (int) (d * 100000000);

            String listKey = "listKey:" + num;

            for (int j = 0; j < 2; j++) {
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
        test2();
    }


}
