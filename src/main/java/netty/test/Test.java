package netty.test;

import netty.gateway.Constants;
import netty.gateway.EndpointManager;
import netty.gateway.register.ProxyRegister;
import netty.gateway.register.ServerRegister;
import org.junit.Before;

public class Test {

    @org.junit.Test
    public void serverRegister(){
        ServerRegister serverRegister = new ServerRegister(Constants.ZK_HOST);
        serverRegister.register();
        System.out.println("连接成功");
        while (true);
    }

    @org.junit.Test
    public void proxyRegister(){
        ProxyRegister register = new ProxyRegister(Constants.ZK_HOST);
        EndpointManager endpointManager = new EndpointManager(register);
        endpointManager.start();
        System.out.println("启动成功");
        while (true);
    }

    @Before
    public void before(){
        System.setProperty("java.net.preferIPv4Stack","true");
    }
}
