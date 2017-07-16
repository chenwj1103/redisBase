package com.base;

import redis.clients.jedis.Jedis;

/**
 * Created by Chen Weijie on 2017/7/16.
 */
public class IncrDemo {

    public static void main(String[] args) {

        Jedis jedis = new Jedis("127.0.0.1", 6379);
        System.out.println("connect success!");
        jedis.del("testKey");
        for (int i =0;i<10;i++){
            long l = jedis.incr("testKey");
            String value = jedis.get("testKey");
            System.out.println(i+1+"次incr命令后，testKey对应的value是："+value);
        }



    }


}
