package test;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo {

    static class Runner implements Runnable {

        private CyclicBarrier barrier;
        private String name;

        public Runner(CyclicBarrier barrier, String name) {
            this.barrier = barrier;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep((new Random().nextInt(8)) * 1000 );
                System.out.println(name + " is ready.[" + System.currentTimeMillis()  + ']');
                // 所有参与者都在此执行await之前，将一直阻塞
                barrier.await();
                // 所有参与者的await方法都执行后，将同时执行下面动作
                System.out.println(name + " run! [" + System.currentTimeMillis()  + "]");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3);

        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(new Thread(new Runner(barrier, "No1 Runner")));
        executor.submit(new Thread(new Runner(barrier, "No2 Runner")));
        executor.submit(new Thread(new Runner(barrier, "No3 Runner")));

        executor.shutdown();
    }
}
