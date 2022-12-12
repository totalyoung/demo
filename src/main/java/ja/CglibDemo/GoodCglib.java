package ja.CglibDemo;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by totalyoung on 2018/11/22.
 */
public class GoodCglib implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("before good");
        proxy.invokeSuper(obj, args);
        return null;
    }
}
