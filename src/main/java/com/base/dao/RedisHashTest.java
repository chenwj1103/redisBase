package com.base.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chen Weijie on 2017/7/23.
 */
public class RedisHashTest extends RedisClient {


    //    hash-max-ziplist-entries  > 512 (520) hash-max-ziplist-value >64(70)
    public static void test1() {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            double d = Math.random();
            int num = (int) (d * 1000000000);

            String mapKey = "mapKey:" + num;
            Map<String, String> values = new HashMap<String, String>();

            for (int j = 0; j < 513; j++) {
                num++;
                String value = "test key length ,need length is gt 64 byte.it is too large ll" + num;
                values.put(value, value);
            }
            System.out.println("mapKey:" + mapKey);
            hMset(mapKey, values);
        }

        long end = System.currentTimeMillis();
        System.out.println("time==========" + (end - start) / 1000);

    }


    //    hash-max-ziplist-entries  < 512 (500)  hash-max-ziplist-value >64(70)
    public static void test2() {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            double d = Math.random();
            int num = (int) (d * 1000000000);

            String mapKey = "mapKey:" + num;
            Map<String, String> values = new HashMap<String, String>();

            for (int j = 0; j < 511; j++) {
                num++;
                String value = "test key length ,need length is gt 64 byte.it is too large ll" + num;
                values.put(value, value);
            }
            System.out.println("mapKey:" + mapKey);
            hMset(mapKey, values);
        }

        long end = System.currentTimeMillis();
        System.out.println("time==========" + (end - start) / 1000);

    }


    //    hash-max-ziplist-entries  > 512 (520)  hash-max-ziplist-value <64(60)
    public static void test3() {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            double d = Math.random();
            int num = (int) (d * 1000000000);

            String mapKey = "mapKey:" + num;
            Map<String, String> values = new HashMap<String, String>();

            for (int j = 0; j < 520; j++) {
                num++;
                String value = "test key length ,need length is gt 64 byte.it is to" + num;
                values.put(value, value);
            }
            System.out.println("mapKey:" + mapKey);
            hMset(mapKey, values);
        }

        long end = System.currentTimeMillis();
        System.out.println("time==========" + (end - start) / 1000);

    }


    //    hash-max-ziplist-entries  < 512 (500)  hash-max-ziplist-value <64(60)
    public static void test4() {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 50000; i++) {
            double d = Math.random();
            int num = (int) (d * 1000000000);

            String mapKey = "mapKey:" + num;
            Map<String, String> values = new HashMap<String, String>();

            for (int j = 0; j < 500; j++) {
                num++;
                String value = "test key length ,need length is gt 64 byte.it is to" + num;
                values.put(value, value);
            }
            System.out.println("mapKey:" + mapKey);
            hMset(mapKey, values);
        }

        long end = System.currentTimeMillis();
        System.out.println("time==========" + (end - start) / 1000);

    }





    public static void main(String[] args) {
        test4();
    }


}
