package ja.CglibDemo;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import org.junit.jupiter.api.Test;

/**
 * Created by totalyoung on 2018/11/22.
 */
public class TestCglib {

    public static void main(String[] args) {
        TestCglib testCglib = new TestCglib();
        testCglib.invokeHello();

    }

    @Test
    public HelloService getProxy(){
        //代理类存入硬盘
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "E:\\ideaSet\\Demo\\com");
        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(HelloService.class);
        enhancer.setCallback(new HelloCglib(new HelloInterfaceImpl()));
//        enhancer.setCallback(new HelloCglib());
        enhancer.setInterfaces(new HelloInterfaceImpl().getClass().getInterfaces());
//        enhancer.setSuperclass(HelloInterface.class);
//        enhancer.setInterfaces(new Class[]{HelloInterface.class});
        Object o = enhancer.create();
        HelloService helloService = (HelloService) o;
//        HelloInterface helloInterface = (HelloInterface) o;
//        helloInterface.sayInterface();
        return helloService;
    }

    @Test
    public HelloService getProxyByMore(){
        //代理类存入硬盘
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "E:\\ideaSet\\Demo\\com");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(HelloService.class);
        Callback[] callback = new Callback[2];
        callback[0]= new HelloCglib();
        callback[1]= new GoodCglib();
        enhancer.setCallbacks(callback);
        enhancer.setCallbackFilter(new TestCallbackFilter());
        Object o = enhancer.create();
        HelloService helloService = (HelloService) o;
        helloService.sayHello();
//        helloService.sayGoodBye();
        return helloService;
    }


    @Test
    public void invokeHello(){
        getProxyByMore().sayHello();
    }


}

