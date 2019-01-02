package ja.jvm.ch2;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by yangst on 2018/8/8.
 * VM args : -XX:PermSize=10M -XX:MaxPermSize=10M
 */
public class JavaMethodArcaOOm {

    public static void main(String[] args) {
        while(true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    System.out.println("---------------");
                    return proxy.invokeSuper(obj,args);
                }
            });
            enhancer.create();
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println();
                }
            });
        }
    }

    static class OOMObject{}
}

