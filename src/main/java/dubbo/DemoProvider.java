package dubbo;

import org.apache.dubbo.config.*;

import java.util.Scanner;

import static dubbo.StringConstant.redisRegistry;
import static dubbo.StringConstant.zookeeperRegistry;

public class DemoProvider {
    public static void main(String[] args) {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("young-app");
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(zookeeperRegistry);
        registryConfig.setTimeout(10000);
//        registryConfig.setAddress(zookeeperRegistry);
        ProtocolConfig protocolConfig=new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(-1);
        protocolConfig.setThreads(20);

        ServiceConfig<DemoService> serviceConfig=new ServiceConfig();
        serviceConfig.setApplication(application);
        serviceConfig.setProtocol(protocolConfig);
        serviceConfig.setRegistry(registryConfig);
        serviceConfig.setInterface(DemoService.class);
        serviceConfig.setRef(new DemoServiceImpl());
        serviceConfig.export();
        while (true){
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            if("quit".equals(s)){
                System.exit(1);
            }
        }
    }
}
