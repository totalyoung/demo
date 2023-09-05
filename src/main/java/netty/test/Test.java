package netty.test;

import netty.gateway.Constants;
import netty.gateway.register.ServerZKClient;
import org.junit.Before;

public class Test {

    @org.junit.Test
    public void serverRegister(){
        ServerZKClient serverZKClient = new ServerZKClient(Constants.ZK_HOST);
        serverZKClient.register();
        System.out.println("连接成功");
        while (true);
    }

    @org.junit.Test
    public void proxyRegister(){
//        ProxyZKClient register = new ProxyZKClient(Constants.ZK_HOST);
//        ProviderManager providerManager = new ProviderManager(register);
//        providerManager.start();
//        System.out.println("启动成功");
//        while (true);
    }

    @Before
    public void before(){
        System.setProperty("java.net.preferIPv4Stack","true");
    }
}
