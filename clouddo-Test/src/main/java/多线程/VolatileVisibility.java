package 多线程;

public class VolatileVisibility {

    public static class TestData {
        volatile int num = 0;
        public void updateNum(){
            num = 1;
        }
    }

    public static void main(String[] args) {
        final TestData testData = new TestData();
        new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("ChildThread num-->"+testData.num);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                testData.updateNum();
                System.out.println("ChildThread update num-->"+testData.num);
            }
        }).start();

        while (testData.num == 0){
        }

        System.out.println("MainThread num-->"+testData.num);
    }
}