package test;

public class VolatileActorTest {
    volatile int i;

    public void addI() {
        i++;
    }
    public static void main(String[] args) throws Exception {
        VolatileActorTest volatileActorTest = new VolatileActorTest();
        for (int i=0; i< 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    volatileActorTest.addI();
                }
            }).start();
        }
        Thread.sleep(1000);//等待10秒，保证上面程序执行完成

        System.out.println(volatileActorTest.i);

    }
}




