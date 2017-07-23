package com.base;

import redis.clients.jedis.Jedis;

/**
 * 连接redis的操作
 * Created by Chen Weijie on 2017/7/6.
 */
public class ConnectRedis {

    public static void main(String[] args) {

        Jedis jedis = new Jedis("10.21.7.32", 6379);
        jedis.auth("test");
        System.out.println("connect success!");
        System.out.println("server is running:" + jedis.ping());

    }

}
