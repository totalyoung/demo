package dubbo;

import java.util.Scanner;

public class LocalInvoker {

    public static void main(String[] args) {
        DemoProvider.export();
        DemoService demoService = DemoConsumer.get();
        while(true){
            Scanner scanner = new Scanner(System.in);
            String s = scanner.nextLine();
            if("quit".equals(s)){
                System.exit(1);
            }
            try {
                System.out.println(demoService.getDomo(123123123));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
