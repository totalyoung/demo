package dubbo;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;

import static dubbo.StringConstant.zookeeperRegistry;

public class ConfigInstance {

    public static ProtocolConfig getProtocol(){
        ProtocolConfig protocolConfig=new ProtocolConfig();
        protocolConfig.setName("dubbo");
//        protocolConfig.setName("injvm");
        protocolConfig.setPort(-1);
        protocolConfig.setThreads(20);
        return protocolConfig;
    }

    public static RegistryConfig getZKRegistry(){
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(zookeeperRegistry);
        registryConfig.setTimeout(10000);
        //zk节点的根路径
//        registryConfig.setGroup("ZK");
        return  registryConfig;
    }

    public static ApplicationConfig getApplication(){
        ApplicationConfig application = new ApplicationConfig();
        application.setName("young-app");
        return application;
    }
}
