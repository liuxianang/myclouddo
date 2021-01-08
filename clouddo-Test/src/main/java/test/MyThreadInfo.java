package test;

public class MyThreadInfo extends Thread {

    @Override // 可以省略
    public void run() {
        System.out.println("run");
        // System.exit(1);
    }

    public static void main(String[] args) {
        MyThreadInfo thread = new MyThreadInfo();
        thread.start();

        System.out.println("线程唯一标识符：" + thread.getId());
        System.out.println("线程名称：" + thread.getName());
        System.out.println("线程状态：" + thread.getState());
        System.out.println("线程优先级：" + thread.getPriority());
    }
}
