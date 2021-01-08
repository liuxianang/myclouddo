package 多线程;

public class TurnsPrintNumber {

    private static Object lock = new Object(); //锁
    private static int i = 1;

    static class Print implements Runnable{
        @Override
        public void run() {
            while (true) {
                synchronized (lock) {
                    if ( i > 20) {
                        System.out.println("打印完毕！");
                        lock.notify();
                        return;
                    }
                    System.out.println("线程" + Thread.currentThread().getName() + "打印："  + i ++ );
                    lock.notify();
                    try {
                        Thread.sleep(100);
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Print print = new Print();
        new Thread(print).start();
        new Thread(print).start();
    }
}

