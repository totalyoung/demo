package dubbo;

import org.apache.dubbo.config.ReferenceConfig;

import java.util.Scanner;

public class DemoConsumer {

    public static DemoService get(){
        ReferenceConfig<DemoService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setApplication(ConfigInstance.getApplication());
        referenceConfig.setInterface(DemoService.class);
//        referenceConfig.setScope("local");
        referenceConfig.setRegistry(ConfigInstance.getZKRegistry());
//        referenceConfig.setProtocol(ConfigInstance.getProtocol().getName());
//        referenceConfig.setLoadbalance("");
        referenceConfig.setTimeout(1000000);

//        referenceConfig.setConnections();
        
        DemoService userService = referenceConfig.get();
        System.out.println("启动成功");
        return userService;
    }
    public static void main(String[] args) {
        DemoService userService =get();
        while(true){
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            if("quit".equals(s)){
                System.exit(1);
            }
            try {
                System.out.println(userService.getDomo(123123123));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
