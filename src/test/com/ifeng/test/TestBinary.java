package com.ifeng.test;

/**
 * Created by Chen Weijie on 2017/7/23.
 */
public class TestBinary {


    public static void main(String[] args) {

        long l=10;

        String string =Long.toBinaryString(l);
        System.out.println(string);


        String str ="testKeyLength++zaddKey测试占用内存空间大小，字节长度";
        System.out.println(str.getBytes().length);



    }

}
