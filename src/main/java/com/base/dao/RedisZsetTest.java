package com.base.dao;

/**
 * Created by Chen Weijie on 2017/7/23.
 */
public class RedisZsetTest extends RedisClient {

//    zset-max-ziplist-entries  >128
//    zset-max-ziplist-value >64

    public  static void test() {

        for (int i = 0; i < 10000; i++) {
            double d = Math.random();
            int num = (int) (d * 100000000);
            for (int j = 0; j < 130; j++) {
                num++;
                String zaddKey = "zaddKey:" + num;
                zAdd(zaddKey, Math.random(), num + "");
                System.out.println("zaddKey=======" + zaddKey);
            }
        }


        for (int i = 0; i < 490000; i++) {

            double d = Math.random();
            int num = (int) (d * 100000000);

            for (int j = 0; j < 2; j++) {
                num++;
                String zaddKey = "zaddKey:" + num;
                zAdd(zaddKey, Math.random(), num + "testKeyLength++zaddKey测试占用内存空间大小，字节长度");
                System.out.println("zaddKey=======" + zaddKey);
            }
        }
    }



    //    zset-max-ziplist-entries  <128
//    zset-max-ziplist-value <64

    public static void test2() {

        for (int i = 0; i < 10000; i++) {
            double d = Math.random();
            int num = (int) (d * 100000000);
            for (int j = 0; j < 2; j++) {
                num++;
                String zaddKey = "zaddKey:" + num;
                zAdd(zaddKey, Math.random(), num + "");
                System.out.println("zaddKey=======" + zaddKey);
            }
        }


        for (int i = 0; i < 490000; i++) {

            double d = Math.random();
            int num = (int) (d * 100000000);

            for (int j = 0; j < 2; j++) {
                num++;
                String zaddKey = "zaddKey:" + num;
                zAdd(zaddKey, Math.random(), num + "testKeyLength++zaddKey测试占用内存空间大小，字节长度");
                System.out.println("zaddKey=======" + zaddKey);
            }
        }
    }

    public static void main(String[] args) {

        test();
//        test2();
    }


}
