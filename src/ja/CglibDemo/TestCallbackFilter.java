package ja.CglibDemo;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

/**
 * Created by totalyoung on 2018/11/23.
 */
public class TestCallbackFilter implements CallbackFilter {
    @Override
    public int accept(Method method) {
        if("sayHello".equals(method.getName())){
            return 0;
        }
        if("sayGoodBye".equals(method.getName())){
            return 1;
        }
       return 0;
    }
}
