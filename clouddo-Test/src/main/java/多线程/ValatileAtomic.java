package 多线程;

public class ValatileAtomic {
    public static class TestData {
        volatile int num = 0;
        //synchronized
        public void updateNum(){
            num++;
        }
    }

    public static void main(String[] args) {
        final TestData testData = new TestData();
        for(int i = 1; i <= 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 1; j <= 1000; j++) {
                        testData.updateNum();
                    }
                }
            }).start();
        }

        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
        System.out.println("最终结果：" + testData.num);
    }
}
