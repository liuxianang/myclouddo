package test;

/**
 * 修改锁的引用导致锁失效
 */
public class eqqwe {
    private String lock = "object lock";

    private void method() {
        synchronized (lock) {
            try {
                System.out.println("start - " + Thread.currentThread().getName()  + " use " + lock);
                // 如果在这里修改了锁的引用，则锁会失效
                lock = "changed object lock";
                Thread.sleep(2000);
                System.out.println("end - " + Thread.currentThread().getName()  + " use " + lock);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        final eqqwe test = new eqqwe();
        Thread t1 = new Thread(() -> test.method(), "t1");
        Thread t2 = new Thread(() -> test.method(), "t2");

        t1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
