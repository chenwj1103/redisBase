package com.base.dao;

/**
 * Created by Chen Weijie on 2017/7/23.
 */
public class RedisListTest extends RedisClient {




//    list-max-ziplist-entries >512
//    list-max-ziplist-value >64
    public static void test() {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 490000; i++) {
            double d = Math.random();
            int num = (int) (d * 100000000);

            String listKey = "listKey:" + num;

            for (int j = 0; j < 10; j++) {
                num++;
                String value = "testKeyLength++mapKey测试占用内存空间大小，字节长度>64:" + num;
                lPush(listKey,value);
            }
            System.out.println("listKey=======" + listKey);
        }

        for (int i = 0; i < 10000; i++) {
            double d = Math.random();
            int num = (int) (d * 100000000);

            String listKey = "listKey:" + num;

            for (int j = 0; j < 520; j++) {
                num++;
                String value = "testKeyLength++mapKey测试占用内存空间大小，字节长度>64:" + num;
                lPush(listKey,value);
            }
            System.out.println("listKey=======" + listKey);
        }

        long end = System.currentTimeMillis();
        System.out.println("time==========" + (end - start) / 1000);

    }



    //    list-max-ziplist-entries <512
//    list-max-ziplist-value <64
    public static void test2() {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 490000; i++) {
            double d = Math.random();
            int num = (int) (d * 100000000);

            String listKey = "listKey:" + num;

            for (int j = 0; j < 10; j++) {
                num++;
                String value = "字节长度<64:" + num;
                lPush(listKey,value);
            }
            System.out.println("listKey=======" + listKey);
        }

        for (int i = 0; i < 10000; i++) {
            double d = Math.random();
            int num = (int) (d * 100000000);

            String listKey = "listKey:" + num;

            for (int j = 0; j < 2; j++) {
                num++;
                String value = "testKeyLength++mapKey测试占用内存空间大小，字节长度>64:" + num;
                lPush(listKey,value);
            }
            System.out.println("listKey=======" + listKey);
        }

        long end = System.currentTimeMillis();
        System.out.println("time==========" + (end - start) / 1000);

    }





    public static void main(String[] args) {
        //test2();
        test();
    }


}
