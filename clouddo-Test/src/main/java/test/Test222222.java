package test;

public class Test222222 {
    public volatile int inc = 0;
    public void increase() {
        inc++;
    }

    public static void main(String[] args) {
        final Test222222 test = new Test222222();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<100;j++)
                        test.increase();
                };
            }.start();
        }
//保证前面的线程都执行完
        while(Thread.activeCount()>1)
            Thread.yield();
        System.out.println(test.inc);
    }
}
