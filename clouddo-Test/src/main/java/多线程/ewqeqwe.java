package 多线程;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ewqeqwe {
    public static void main(String[] args) {
    ExecutorService threadPool = Executors.newFixedThreadPool(10);         while(true) {
        threadPool.execute(new Runnable() {
            // 提交多个线程任务，并执行
             @Override               
             public void run() {
             System.out.println(Thread.currentThread().getName() + " is running ..");
             try {                         Thread.sleep(3000);
             } catch (InterruptedException e) {
             e.printStackTrace();                     }
            }
             });
             }
             }
        }

