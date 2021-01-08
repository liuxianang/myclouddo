package test;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class lambda和函数式编程 {
    @Test
    public void test1() {
        List names = Arrays.asList("peter", "anna", "mike", "xenia");

        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });
        System.out.println(Arrays.toString(names.toArray()));
    }

    @Test
    public void test2() {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        Collections.sort(names, (String a, String b) -> {
            return b.compareTo(a);
        });

        Collections.sort(names, (String a, String b) -> b.compareTo(a));

        Collections.sort(names, (a, b) -> b.compareTo(a));
        System.out.println(Arrays.toString(names.toArray()));
    }


    static void add(double a, String b) {
        System.out.println(a + b);
    }

    @Test
    public void test5() {
        D d = (a, b) -> add(a, b);
//        interface D {
//            void get(int i,String j);
//        }
        //这里要求，add的两个参数和get的两个参数吻合并且返回类型也要相等，否则报错
//        static void add(double a,String b) {
//            System.out.println(a + b);
//        }
    }

    @FunctionalInterface
    interface D {
        void get(int i, String j);
    }
}