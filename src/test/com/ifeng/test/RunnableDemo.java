package com.ifeng.test;

/**
 * Created by Chen Weijie on 2017/7/23.
 */
public class RunnableDemo implements Runnable {


    private String threadName;

    RunnableDemo(String threadName) {
        this.threadName = threadName;
        System.out.println("Creating " + threadName);
    }

    public void run() {

        System.out.println("Running:" + threadName);

//        try {
//            for (int i = 4; i > 0; i--) {
//                System.out.println("Thread: " + threadName + ", " + i);
//                Thread.sleep(50);
//            }
//        } catch (InterruptedException e) {
//            System.out.println("Thread " + threadName + " interrupted.");
//        }
//        System.out.println("Thread " + threadName + " exiting.");

    }


    public void start() {
        System.out.println("Starting " + threadName);

        Thread thread = new Thread(this, threadName);
        thread.start();

    }


    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }
}
