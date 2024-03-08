package com.collegesoft.carservice;

class MyThread extends Thread {
    public void run() {
        // Code to be executed in parallel
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getId() + " Value " + i);
        }
    }
}

public class ConcurrencySampleDemo {
    public static void main(String args[]) {
        MyThread thread1 = new MyThread();
        MyThread thread2 = new MyThread();

        // Start both threads
        thread1.start();
        thread2.start();
    }
}