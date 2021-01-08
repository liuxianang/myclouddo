package 多线程;

import java.util.ArrayList;
import java.util.List;



public class UnsafeTest03 implements Runnable{
    static List<String> list = new ArrayList<String>();
    public static void main(String[] args) {

        for (int i = 0; i < 10000; i++) {
            UnsafeTest03 u03 =new UnsafeTest03();
            Thread t = new Thread(u03);
            t.start();
        }
        System.out.println(list.size());
    }

    @Override
    public void run() {
        list.add(Thread.currentThread().getName());
    }

}

