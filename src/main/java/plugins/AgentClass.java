package plugins;


import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.Properties;

public class AgentClass {

    private static boolean isStart;

    public static void main(String[] args) throws InterruptedException {

//        System.out.println("AgentClass invoke!!");
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        String name = runtimeMXBean.getName();
        Properties properties = System.getProperties();
        Thread.sleep(50000);
        System.out.println("----------------------------------------------");
        Pid pid = new Pid();
//        while (true){
//
//        }
    }
}
