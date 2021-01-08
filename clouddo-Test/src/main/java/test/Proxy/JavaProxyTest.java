package test.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@SuppressWarnings("restriction")
public class JavaProxyTest {
    public static void main(String[] args) throws Exception {
        JavaProxyInterface javaProxyInterface = new ConcreteClass();

        JavaProxyInterface newJavaProxyInterface = (JavaProxyInterface) Proxy.newProxyInstance(
                JavaProxyTest.class.getClassLoader(), new Class[] { JavaProxyInterface.class },
                new MyInvocationHandler(javaProxyInterface));
        //这里可以看到这个类以及被代理，在执行方法前会执行aopMethod（）。这里需要注意的是oneDay（）方法和oneDayFinal（）的区别。oneDayFinal的方法aopMethod执行1次，oneDay的aopMethod执行1次
        newJavaProxyInterface.gotoSchool();
        newJavaProxyInterface.gotoWork();
        newJavaProxyInterface.oneDayFinal();
        newJavaProxyInterface.oneDay();
    }
}
/**
 * InvocationHandler 的一个实现，实际上处理代理的逻辑在这里
 */
class MyInvocationHandler implements InvocationHandler {
    JavaProxyInterface javaProxy;
    public MyInvocationHandler(JavaProxyInterface javaProxy) {
        this.javaProxy = javaProxy;
    }
    private void aopMethod() {
        System.out.println("before method");
    }
    //继承方法，代理时实际执行的犯法，如果要实现原方法，则需要调用method.invoke(javaProxy, args)，这里还调用了一个aopMethod(),可以类比于Spring中的切面before注解。
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        aopMethod();
        return method.invoke(javaProxy, args);
    }
}
/**
 * 需要一个最顶层接口，必须
 */
interface JavaProxyInterface {
    void gotoSchool();
    void gotoWork();
    void oneDay();
    void oneDayFinal();
}
/**
 * 需要被代理的类，实现了顶层接口，非必须
 */
class ConcreteClass implements JavaProxyInterface {
    @Override
    public void gotoSchool() {
        System.out.println("gotoSchool");
    }
    @Override
    public void gotoWork() {
        System.out.println("gotoWork");
    }
    @Override
    public void oneDay() {
        gotoSchool();
        gotoWork();
    }
    @Override
    public final void oneDayFinal() {
        gotoSchool();
        gotoWork();
    }
}
