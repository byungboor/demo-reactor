package com.example.demo.reactor.example;

import java.util.concurrent.TimeUnit;

public class DaemonThreadApplication {

    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(()-> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ThreadName : " + Thread.currentThread().getName());
        });
        a.start();
        System.out.println("-------------------END");
        TimeUnit.SECONDS.sleep(10);
    }
}
