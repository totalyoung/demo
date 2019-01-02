package ja.jvm.ch3;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * Created by yangst on 2018/8/9.
 */

public class MemoryAllocation {

    public static final int _1MB = 1024 * 1024;

    /**
     * VN args : -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    public static void testAllocation(){
        byte[] allocation1, allocation2, allocation3, allocation4,allocation5;
        allocation1 = new byte[2*_1MB];
        allocation2 = new byte[2*_1MB];
        allocation3 = new byte[2*_1MB];
        allocation4 = new byte[4*_1MB];
    }

    /**
     * VN args : -verbose：gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails-XX:SurvivorRatio=8 -XX:PretenureSizeThreshold=3145728
     * PretenureSizeThreshold参数只对Serial和ParNew两款收集器有效，Parallel Scavenge收集器不认识这个参数，Parallel Scavenge收集器一般并不需要设置
     */
    public static void testPretenureSizeThreshold(){
        byte[] allocation;
        allocation = new byte[3*_1MB];
    }

    /**
     * VM参数：-verbose:gc-Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
     */
    public static  void testTenuringThreshold(){
        byte[] allocation1,allocation2,allocation3;
        allocation1=new byte[_1MB/4];
        //什么时候进入老年代取决于XX：MaxTenuringThreshold设置
        allocation2=new byte[4*_1MB];
        allocation3=new byte[4*_1MB];
        allocation3=null;
        allocation3=new byte[4*_1MB];
    }

    /**
     *VM参数：-verbose:gc-Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution
     */
    public static  void testTenuringThreshold2(){
        byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1=new byte[_1MB/4];
        //allocation1+allocation2大于survivo空间一半
        allocation2=new byte[_1MB/4];
        allocation3=new byte[4*_1MB];
        allocation4=new byte[4*_1MB];
        allocation4=null;
        allocation4=new byte[4*_1MB];
    }

    public static void getJVMName(){
        List<GarbageCollectorMXBean> l = ManagementFactory.getGarbageCollectorMXBeans();
        for(GarbageCollectorMXBean b : l) {
            System.out.println(b.getName());
        }

    }
    public static void main(String[] args) {
        testTenuringThreshold();
    }
}
