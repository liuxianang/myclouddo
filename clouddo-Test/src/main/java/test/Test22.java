package test;

public class Test22 {
    public static void main(String[] args) {
        MQueue queue =  new MQueue(5);

        new Thread(() -> {
            queue.put("1");
            queue.put("2");
            queue.put("3");
            queue.put("4");
            queue.put("5");
            queue.put("6");
        }, "put-thread-1").start();

        new Thread(() -> {
            queue.put("11");
            queue.put("12");
            queue.put("13");
            queue.put("14");
            queue.put("15");
            queue.put("16");
        }, "put-thread-1").start();

        new Thread(() -> {
            queue.take();
            queue.take();
            queue.take();
            queue.take();
            queue.take();
        }, "take-thread-1").start();

        new Thread(() -> {
            queue.take();
            queue.take();
            queue.take();
            queue.take();
            queue.take();
        }, "take-thread-2").start();
    }
}
