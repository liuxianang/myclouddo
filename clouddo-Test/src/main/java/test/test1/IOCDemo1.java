package test.test1;

import net.sf.cglib.proxy.Enhancer;

public class IOCDemo1 {
    public static void main(String args[]) {
   /*     MainService mainService = new MainServiceImpl();
        MainService staticProxy = new StaticProxy(mainService);
        staticProxy.doSomeThing();*/

     /*       MainService mainService=new MainServiceImpl();
            DynamicProxy dynamicProxy=new DynamicProxy(mainService);
            ((MainService)dynamicProxy.getProxy()).doSomeThing();*/
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MainServiceImpl.class);
        enhancer.setCallback(new CglibInterceptor());
        MainService proxy= (MainService)enhancer.create();
        proxy.doSomeThing();
    }
}