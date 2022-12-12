package plugins;

import sun.jvm.hotspot.oops.InstanceKlass;
import sun.jvm.hotspot.tools.jcore.ClassDump;
import sun.jvm.hotspot.tools.jcore.ClassFilter;
import sun.tools.jps.Jps;

public class TestClassFilter implements ClassFilter {

    private String name = "PidUtil";

//    public TestClassFilter(String name) {
//        this.name = name;
//    }

    @Override
    public boolean canInclude(InstanceKlass instanceKlass) {
        if(name ==null) return false;
        String s = instanceKlass.getName().asString();
//        System.out.println(s);
        return s.contains(name);
    }

    public static void main(String[] args) {
        ClassDump cd = new ClassDump();
        cd.start();
    }
}
