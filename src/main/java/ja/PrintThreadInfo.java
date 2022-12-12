package ja;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;

/**
 * Created by totalyoung on 2018/10/18.
 */
public class PrintThreadInfo {
    public static void main(String[] args) {
        ThreadInfo[] threadInfos = ManagementFactory.getThreadMXBean().dumpAllThreads(false, false);
        for (ThreadInfo threadInfo : threadInfos){
            System.out.println("[" + threadInfo.getThreadId() + "]" + threadInfo.getThreadName());
        }

    }

}
