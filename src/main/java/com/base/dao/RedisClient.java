package com.base.dao;

import com.base.utils.RedisUtil;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * redis的基本方法
 * Created by Chen Weijie on 2017/7/22.
 */
public abstract  class RedisClient {


  public static final Jedis jedis = RedisUtil.getJedis();


    /**
     * String 类型
     * 设置 key value
     * @param key key
     * @param value 要设置的值
     * @return
     */
    public static String set(String key, String value) {
        return jedis.set(key, value);
    }

    /**
     * String 类型
     *
     * @param key key
     * @return 返回get请求获取的字符串
     */
    public static String get(String key){
        return jedis.get(key);
    }

    /**
     * 设置hash（批量）
     * @param key
     * @param mapValue
     * @return
     */
    public static String hMset(String key , Map<String,String> mapValue){
        return jedis.hmset(key,mapValue);
    }

    /**
     * 设置hash单个字段
     * @param key
     * @param filed
     * @param value
     * @return
     */
    public static long hSet(String key,String filed,String value){
        return jedis.hset(key,filed,value);
    }

    /**
     * 获取hash的多个字段的值
     * @param key
     * @param fileds
     * @return
     */
    public static List<String> hMget(String key, String ... fileds){
        return jedis.hmget(key,fileds);
    }

    /**
     * 获取某个key的所有值
     * @param key
     * @return
     */
    public static Map<String, String> hGetAll(String key){
        return jedis.hgetAll(key);
    }

    /**
     * 从左端添加元素
     * @param key
     * @param values
     * @return
     */
    public static long lPush(String key,String ... values){
        return jedis.lpush(key,values);
    }

    /**
     * 从右端添加元素
     * @param key
     * @param values
     * @return
     */
    public static long rPush(String key,String ... values){
        return jedis.rpush(key,values);
    }

    /**
     * 从左端取出一个元素
     * @param key
     * @return
     */
    public static String lPop(String key){
        return jedis.lpop(key);
    }

    /**
     * 从右端取出一个元素
     * @param key
     * @return
     */
    public static String rPop(String key){
        return jedis.rpop(key);
    }


    /**
     * 获取list的某段范围的元素
     * @param key list的key
     * @param start 开始的位置
     * @param end 结束的位置
     * @return 集合
     */
    public static List<String> lrange(String key,long start,long end){
        return jedis.lrange(key,start,end);
    }

    /**
     * 在set集合中添加元素
     * @param key
     * @param members
     * @return
     */
    public static long sAdd(String key,String ... members){
        return jedis.sadd(key,members);
    }

    /**
     * 获取一个集合中的count个元素
     * @param key
     * @return
     */
    public static Set<String> sMembers(String key,long count){
        return jedis.spop(key,count);
    }

    /**
     * 在有序集合中添加元素
     * @param key
     * @param membersScore
     * @return
     */
    public static long zAdd(String key , Map<String,Double> membersScore) {
        return jedis.zadd(key,membersScore);
    }

    /**
     * 在有序集合中添加一个元素
     * @param key
     * @param score
     * @param member
     * @return
     */
    public static long zAdd(String key,double score,String member){
        return jedis.zadd(key,score,member);
    }

    /**
     * 获取多个元素
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static Set<String> zRange(String key,long start,long end){
        return jedis.zrange(key,start,end);
    }


}
