package com.ifeng.test;

/**
 * Created by Chen Weijie on 2017/7/23.
 */
public class TestThread {

    public static void main(String[] args) {

        RunnableDemo r1 = new RunnableDemo("thread1");

        r1.start();

        RunnableDemo r2 = new RunnableDemo("thread2");
        r2.start();

        RunnableDemo r3 = new RunnableDemo("thread3");

        r3.start();

        RunnableDemo r4 = new RunnableDemo("thread4");
        r4.start();

    }

}
