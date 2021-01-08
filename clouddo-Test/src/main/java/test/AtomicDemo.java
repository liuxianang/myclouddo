package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    public static AtomicInteger sum = new AtomicInteger(0);

    public static void add() {
        System.out.println(Thread.currentThread().getName() + " 初始sum = " + sum);
        for (int i = 0 ; i < 10000; i ++ ) {
            sum.addAndGet(1);
        }
        System.out.println(Thread.currentThread().getName() + " 相加后sum = " + sum);
    }

    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            es.submit(() -> add());
        }
        es.shutdown();

        while (true) {
            if (es.isTerminated()) {
                System.out.println("finally sum = " + sum.get());
                break;
            }
        }
    }
}
