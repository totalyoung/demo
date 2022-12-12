package plugins;

import com.sun.media.jfxmediaimpl.HostUtils;
import sun.jvm.hotspot.runtime.VM;
import sun.jvm.hotspot.tools.jcore.ClassDump;
import sun.jvmstat.monitor.*;
import sun.tools.jps.Arguments;
import sun.tools.jps.Jps;

import java.net.URISyntaxException;
import java.util.Set;

public class PidUtil {

    public static void jps() throws MonitorException, URISyntaxException, InterruptedException {
        Arguments arguments = new Arguments(new String[0]);
        HostIdentifier hostIdentifier = arguments.hostId();
        MonitoredHost monitoredHost = MonitoredHost.getMonitoredHost(hostIdentifier);
        Set<Integer> integers = monitoredHost.activeVms();
//        VM vm1 = VM.getVM();
        MonitoredVm vm;
        System.setProperty("sun.jvm.hotspot.tools.jcore.filter","plugins.TestClassFilter");
        for (Integer vmid : integers) {
            String vmidString = "//" + vmid + "?mode=r";
            VmIdentifier id = new VmIdentifier(vmidString);
            vm = monitoredHost.getMonitoredVm(id, 0);
            String s = MonitoredVmUtil.mainClass(vm, arguments.showLongPaths());
            System.out.println(vmid +" "+s);
            boolean b = "Launcher".equals(s);
            PidUtil.class.getSimpleName().equals(s);
            if(b){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ClassDump.main(new String[]{vmid+""});
                    }
                }).start();

            }

        }
        VM vm1 = VM.getVM();
        System.out.println();
        while (true);
//        Pid.test();
//        System.getProperty("adsf");
//        System.out.println();
//        for (int i = 0; i < 10; i++) {
//
//            Thread.sleep(100000000000L);
//        }

//        Iterator var4 = var3.iterator();
    }

    public static void main(String[] args) throws Exception {
//        jps();
        System.out.println(System.getProperty("young.my"));
        while(true){

        }
    }
}
