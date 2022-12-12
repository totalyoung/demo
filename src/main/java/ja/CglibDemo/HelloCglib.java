package ja.CglibDemo;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by totalyoung on 2018/11/22.
 */
public class HelloCglib implements MethodInterceptor {

    private HelloInterface helloInterface;
    public HelloCglib(HelloInterface helloInterface) {
        this.helloInterface = helloInterface;
    }

    public HelloCglib() {
    }

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("before sayHello");
        try{
            methodProxy.invokeSuper(obj,args);
        }catch (Exception e){
            System.out.println("Exception");
        }
        return null;
    }


}
