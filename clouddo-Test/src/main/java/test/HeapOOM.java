package test;

import java.util.ArrayList;
import java.util.List;

public class HeapOOM {
    //创建一个内部类用于创建对象使用
    static class OOMObject {
    }
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        //无限创建对象，在堆中
        while (true) {
            list.add(new OOMObject());
        }
    }
}
