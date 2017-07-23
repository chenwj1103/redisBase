package com.base.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chen Weijie on 2017/7/23.
 */
public class RedisHashTest extends RedisClient {


    public static void test() {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            double d = Math.random();
            int num = (int) (d * 100000000);

            String mapKey = "mapKey:" + num;
            Map<String, String> values = new HashMap<String, String>();

            for (int j = 0; j < 10; j++) {
                num++;
                String value = "testKeyLength++mapKey测试占用内存空间大小，字节长度时71:" + num;
                values.put(value, value);
            }
            System.out.println("mapKey=======" + mapKey);
            hMset(mapKey, values);
        }

        for (int i = 0; i < 500000; i++) {
            double d = Math.random();
            int num = (int) (d * 100000000);

            String mapKey = "mapKey:" + num;
            Map<String, String> values = new HashMap<String, String>();

            for (int j = 0; j < 520; j++) {
                num++;
                String value = "testKeyLength++mapKey测试占用内存空间大小，字节长度时71:" + num;
                values.put(value, value);
            }
            System.out.println("mapKey=======" + mapKey);
            hMset(mapKey, values);
        }

        long end = System.currentTimeMillis();
        System.out.println("time==========" + (end - start) / 1000);

    }

    public static void main(String[] args) {
        test();
    }


}
