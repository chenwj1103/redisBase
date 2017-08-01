package com.base.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chen Weijie on 2017/7/23.
 */
public class RedisHashTest extends RedisClient {


    //    hash-max-ziplist-entries  = 515
    //    hash-max-ziplist-value = 70
    public static void test1() {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            double d = Math.random();
            int num = (int) (d * 1000000000);

            String mapKey = "mapKey:" + num;
            Map<String, String> values = new HashMap<String, String>();

            for (int j = 0; j < 515; j++) {
                num++;
                String value = "test key length ,need length is 64 byte.it is too much long  " + num;
                values.put(num+"", value);
            }
            System.out.println(mapKey);
            hMset(mapKey, values);
        }

        long end = System.currentTimeMillis();
        System.out.println("time:" + (end - start) / 1000);

    }

    public static void main(String[] args) {
        test1();
    }


}
