package test;

public class ThreadLocalDemo {

    public static void main(String[] args) throws InterruptedException {
        // 不同线程中ThreadLocal变量会各自保存一份副本
        final ThreadLocal<Integer> th = new ThreadLocal<>();

        new Thread(() -> {
            try {
                th.set(100);
                System.out.println("t1 set th = " + th.get());
                Thread.sleep(2000); //保证t1与t2线程在同时执行
                System.out.println("t1 get th = " + th.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        Thread.sleep(1000);

        new Thread(() -> {
            Integer i = th.get();
            System.out.println("t2 get th = " + i);
            th.set(200);
            System.out.println("t2 get th = " + th.get());
        }).start();
    }
}
