package dubbo;

import org.apache.dubbo.config.ProviderConfig;
import org.apache.dubbo.config.ServiceConfig;

import java.util.Scanner;

public class DemoProvider {

    public static void export(){
        ProviderConfig providerConfig = new ProviderConfig();
//        providerConfig.setGroup("group2");
        providerConfig.setRegistry(ConfigInstance.getZKRegistry());
        providerConfig.setProtocol(ConfigInstance.getProtocol());
        providerConfig.setApplication(ConfigInstance.getApplication());

        ServiceConfig<DemoService> serviceConfig=new ServiceConfig();
        serviceConfig.setProvider(providerConfig);
//        serviceConfig.setApplication(ConfigInstance.getApplication());
//        serviceConfig.setProtocol(ConfigInstance.getProtocol());
//        serviceConfig.setGroup("group3");
//        serviceConfig.setScope("local");
//        serviceConfig.setRegistry(ConfigInstance.getZKRegistry());
        serviceConfig.setInterface(DemoService.class);
        serviceConfig.setRef(new DemoServiceImpl());
        serviceConfig.export();
        System.out.println("启动成功");
    }
    public static void main(String[] args) {
        export();
        while (true){
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            if("quit".equals(s)){
                System.exit(1);
            }
        }
    }
}
