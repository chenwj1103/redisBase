package com.base.dao;

/**
 * Created by Chen Weijie on 2017/7/23.
 */
public class RedisExample extends RedisClient {


    public static void test() {

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            double d =Math.random();
            int num = (int)(d*100000000);

            for (int j=0 ;j<100;j++){
                num++;
                String StringKey = "testKeyLength++++++++++++++++++++++++++++++++++num:" + num;
                set(StringKey, num+"");
                System.out.println("StringKey======="+StringKey);
            }

            for (int j=0 ;j<100;j++){
                num++;
                String listKey ="testKeyLength++++++++++++++++++++++++++++++++++listKey:"+num;
                lPush(listKey,num+"");
                System.out.println("listKey======="+listKey);
            }

            for (int j=0 ;j<100;j++){
                num++;
                String mapKey ="testKeyLength++++++++++++++++++++++++++++++++++mapKey:"+num;
                hSet(mapKey,"test",num+"");
                System.out.println("mapKey======="+mapKey);
            }


            for (int j=0 ;j<100;j++){
                num++;
                String setKey ="testKeyLength++++++++++++++++++++++++++++++++++setKey:"+num;
                sAdd(setKey,num+"");
                System.out.println("setKey======="+setKey);
            }


            for (int j=0 ;j<100;j++){
                num++;
                String zaddKey ="testKeyLength++++++++++++++++++++++++++++++++++zaddKey:"+num;
                zAdd(zaddKey,Math.random(),num+"");
                System.out.println("zaddKey======="+zaddKey);
            }


        }
        long end = System.currentTimeMillis();

        System.out.println("time==========" + (end - start) / 1000);

    }

    public static void main(String[] args) {
        test();
    }

}
