package test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestCallable {
    //创建线程类
    public static class MyTestCallable implements Callable {
        public String call() throws Exception {
            return "Hello World";
        }
    }
    public static void main(String[] args) {
        MyTestCallable mMyTestCallable= new MyTestCallable();
        ExecutorService mExecutorService = Executors.newSingleThreadExecutor();
        Future mfuture = mExecutorService.submit(mMyTestCallable);
        try {
//等待线程结束，并返回结果
            System.out.println(mfuture.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

