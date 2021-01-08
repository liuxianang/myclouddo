package test;

public class TestThread extends Thread{
    public void run() {
        System.out.println("Hello World");
    }
    public static void main(String[] args) {
        Thread mThread = new TestThread();
        mThread.start();
    }
}
