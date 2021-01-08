package test;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueDemo {

    /**
     * 在没有其他线程调用queue.take() | queue.poll()方法时，put(E e)方法会一直阻塞
     */
    static void testPut() {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();
        try {
            System.out.println("test put is running");
            queue.put(1);
            System.out.println("test put is over");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 只有take()方法先执行，后序的put()方法才能继续执行，而不被阻塞
     * 如果没有put()方法执行，take()方法将阻塞
     * 如果take()方法执行完，还有put()方法没有执行，则阻塞
     */
    static void testTakeAndPut() {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();

        new Thread(() -> {
            System.out.println("take thread running");
            try {
                System.out.println("take - " + queue.take());
                System.out.println("take - " + queue.take());
                System.out.println("take - " + queue.take());
                System.out.println("take - " + queue.take());
                System.out.println("take - " + queue.take());
                System.out.println("take thread is over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            System.out.println("put thread running");
            try {
                queue.put(6);
                queue.put(7);
                queue.put(8);
                queue.put(9);
                queue.put(10);
                System.out.println("put thread is over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * 只有poll()方法先执行，后序的put()方法才能继续执行，而不被阻塞
     * 如果没有put()方法执行，poll()方法将返回null
     */
    static void testTakeAndPoll() {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();

        new Thread(() -> {
            System.out.println("poll thread running");
            System.out.println("poll - " + queue.poll());
            System.out.println("poll - " + queue.poll());
            System.out.println("poll - " + queue.poll());
            System.out.println("poll - " + queue.poll());
            System.out.println("poll - " + queue.poll());
            System.out.println("poll - " + queue.poll());
            System.out.println("poll - " + queue.poll());
            System.out.println("pool thread is over");
        }).start();

        new Thread(() -> {
            System.out.println("put thread running");
            try {
                queue.put(1);
                queue.put(2);
                queue.put(3);
                queue.put(4);
                queue.put(5);
                System.out.println("put thread is over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * 只有poll()方法先执行，后序的put()方法才能继续执行，而不被阻塞
     * 如果没有put()方法执行, poll(long timeout, TimeUnit timeunit)方法在阻塞timeout时间之后将返回null
     */
    static void testTakeAndPollWithExcepiton() {
        SynchronousQueue<Integer> queue = new SynchronousQueue<>();

        new Thread(() -> {
            System.out.println("poll thread running");
            try {
                System.out.println("poll - " + queue.poll());
                System.out.println("poll - " + queue.poll());
                System.out.println("poll - " + queue.poll());
                System.out.println("poll - " + queue.poll());
                System.out.println("poll - " + queue.poll());
                System.out.println("poll - " + queue.poll());
                System.out.println("poll - " + queue.poll(2, TimeUnit.SECONDS));
                System.out.println("pool thread is over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            System.out.println("put thread running");
            try {
                queue.put(1);
                queue.put(2);
                queue.put(3);
                queue.put(4);
                queue.put(5);
                System.out.println("put thread is over");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        testPut();
        testTakeAndPut();
        testTakeAndPoll();
        testTakeAndPollWithExcepiton();
    }
}
