package test;

import org.junit.jupiter.api.Test;

public class 局部内部类 {
    class A {//局部内部类就是写在方法里的类，只在方法执行时加载，一次性使用。
        public void test() {
            class B {
                public void test () {
                    class C {

                    }
                }
            }
        }
    }
    @Test
    public void test () {
        int i = 1;
        final int j = 2;
        class A {
            @Test
            public void test () {
                System.out.println(i);
                System.out.println(j);
            }
        }
        A a = new A();
        System.out.println(a);
    }

    static class B {
        public static void test () {
            //static class A报错，方法里不能定义静态内部类。
            //因为只有在方法调用时才能进行类加载和初始化。

        }
    }
}