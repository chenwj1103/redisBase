package com.base.dao;

/**
 * Created by Chen Weijie on 2017/7/23.
 */
public class RedisZsetTest extends RedisClient {

//    zset-max-ziplist-entries  128
//    zset-max-ziplist-value 64  70

    public  static void test1() {

        for (int i = 0; i < 1000; i++) {
            double d = Math.random();
            int num = (int) (d * 100000000);
            String zaddKey = "zaddKey:" + num;
            for (int j = 0; j < 140; j++) {
                num++;
                zAdd(zaddKey, Math.random(), "test key length ,need length is gt 64 byte.it is too large ll"+num);
            }
            System.out.println("zaddKey=======" + zaddKey);
        }

    }




    public static void main(String[] args) {

       test1();
    }


}
