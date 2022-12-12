package dubbo;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;

import java.util.Scanner;

import static dubbo.StringConstant.redisRegistry;
import static dubbo.StringConstant.zookeeperRegistry;

public class DemoConsumer {
    public static void main(String[] args) {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("young-app");

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(zookeeperRegistry);
        registryConfig.setTimeout(10000);

        ReferenceConfig<DemoService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(application);
        referenceConfig.setInterface(DemoService.class);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setLoadbalance("");
        referenceConfig.setTimeout(1000);
        DemoService userService = referenceConfig.get();
        while(true){
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            if("quit".equals(s)){
                System.exit(1);
            }
            System.out.println(userService.getDomo());
        }

    }
}
