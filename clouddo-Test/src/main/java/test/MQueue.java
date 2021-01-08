package test;

import java.util.ArrayList;
import java.util.List;

public class MQueue {
    private int maxSize;
    private List<String> list = new ArrayList<>();

    private final Object lock = new Object();

    public MQueue(int maxSize) {
        this.maxSize = maxSize;
        System.out.println("线程-" + Thread.currentThread().getName() + "已初始化长度为"  + this.maxSize + "的队列");
    }

    public void put(String element) {
        synchronized (lock) {
            if (this.list.size() == this.maxSize) {
                try {
                    System.out.println("线程-" + Thread.currentThread().getName() + "队列已满 put waiting");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.list.add(element);
            System.out.println("线程-" + Thread.currentThread().getName() + "向队列中加入元素：" + element);
            lock.notifyAll();
        }
    }

    public String take() {
        synchronized (lock) {
            if (this.list.size() == 0) {
                try {
                    System.out.println("线程-" + Thread.currentThread().getName() + "队列已空 take waiting");
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            String result = this.list.remove(0);
            System.out.println("线程-" + Thread.currentThread().getName() + "从队列中取出元素：" + result);
            lock.notifyAll();
            return result;
        }
    }
}
