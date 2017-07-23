package com.base.dao;

/**
 * Created by Chen Weijie on 2017/7/23.
 */
public class RedisBinaryTest extends RedisClient {


    public static void test() {

        for (int i = 0; i < 500000; i++) {
            double d = Math.random();
            long num = (long) (d * 100000000);
            String longKey = "longKey:" + num;
            set(longKey, num + "");
            System.out.println("longKey=======" + longKey);
        }

    }


    public static void testBinary() {

        for (int i = 0; i < 500000; i++) {
            double d = Math.random();
            long num = (long) (d * 100000000);
            String binaryValue = Long.toBinaryString(num);
            String longKey = "longKey:" + num;
            set(longKey, binaryValue);
            System.out.println("longKey=======" + longKey);
        }

    }

    public static void main(String[] args) {

        test();
//        testBinary();

    }


}
