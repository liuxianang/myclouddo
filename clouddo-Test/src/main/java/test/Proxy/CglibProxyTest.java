package test.Proxy;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
public class CglibProxyTest {
    public static void main(String[] args) throws Exception {
        CglibTestSon CglibTestSon = new CglibTestSon();
        Enhancer enhancer = new Enhancer();
        Callback s = new MthdInvoker(CglibTestSon);
        enhancer.setSuperclass(CglibTestSon.class);
        Callback callbacks[] = new Callback[] { s };
        enhancer.setCallbacks(callbacks);
        CglibTestSon CglibTestSon2 = (CglibTestSon) enhancer.create();
        CglibTestSon2.gotoHome();
        CglibTestSon2.gotoSchool();
        ////这里可以看到这个类以及被代理，在执行方法前会执行aopMethod（）。这里需要注意的是oneDay（）方法和onedayFinal（）的区别。onedayFinal的方法aopMethod执行2次，oneDay的aopMethod执行1次 ,注意这里和jdk的代理的区别
        CglibTestSon2.oneday();
        CglibTestSon2.onedayFinal();
    }
}
/**
 * 需要被代理的类，不需要实现顶层接口
 */
class CglibTestSon {
    public CglibTestSon() {
    }
    public void gotoHome() {
        System.out.println("============gotoHome============");
    }
    public void gotoSchool() {
        System.out.println("===========gotoSchool============");
    }
    public void oneday() {
        gotoHome();
        gotoSchool();
    }
    public final void onedayFinal() {
        gotoHome();
        gotoSchool();
    }
}
/**
 * 可以类比于jdk动态代理中的InvocationHandler ，实际上被代理后重要的类，实际上后续执行的就是intercept里的方法，如果需要执行原来的方法，则调用 method.invoke(s, args);这里也加了一个aopMethod();
 */
class MthdInvoker implements MethodInterceptor {
    private CglibTestSon s;
    public MthdInvoker(CglibTestSon s) {
        this.s = s;
    }
    private void aopMethod() {
        System.out.println("i am aopMethod");
    }
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        aopMethod();
        Object a = method.invoke(s, args);
        return a;
    }
}
