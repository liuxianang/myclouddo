package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreDemo {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(2);

        for (int i = 0 ; i < 5; i ++) {
            Runnable run = () -> {
                try {
                    System.out.println(Thread.currentThread() + "try to require permit");
                    // 获取许可
                    semaphore.acquire();
                    System.out.println(Thread.currentThread() + "require permit.");
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread() + "release permit");
                    // 释放许可，如果不释放，所有未获取到许可的线程将被阻塞
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            executorService.submit(run);
        }
        executorService.shutdown();
    }
}
