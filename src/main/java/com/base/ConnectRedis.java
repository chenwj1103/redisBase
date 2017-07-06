package com.base;

import redis.clients.jedis.Jedis;

/**
 * 连接redis的操作
 * Created by Chen Weijie on 2017/7/6.
 */
public class ConnectRedis {

    public static void main(String[] args) {

        Jedis jedis = new Jedis("127.0.0.1", 6379);
        System.out.println("connect success!");
        jedis.auth("password");
        System.out.println("server is running:" + jedis.ping());

    }

}
