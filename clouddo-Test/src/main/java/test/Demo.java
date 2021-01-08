package test;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Demo {
    public static void main(String[] args) throws InterruptedException {
        Runnable r = new Runnable() {
            public void run() {
                for (int time = 10; time > 0; --time) {

                    System.out.println("Time #" + time);
                }
            }
        };

        Thread t = new Thread(r);
        t.setDaemon(true);  // try to set this to "false" and see what happens
        t.start();

        System.out.println("Main thread waiting...");
        SECONDS.sleep(6);
        System.out.println("Main thread exited.");
    }
}

