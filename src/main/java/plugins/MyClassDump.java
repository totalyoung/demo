package plugins;

import sun.jvm.hotspot.HotSpotAgent;
import sun.jvm.hotspot.tools.jcore.ClassDump;

public class MyClassDump extends ClassDump {

    private int pid;

    public MyClassDump() {
    }

    public MyClassDump(HotSpotAgent hotSpotAgent,int pid) {
        setAgent(hotSpotAgent);
        this.pid = pid;
    }

    @Override
    public void start() {
        HotSpotAgent agent = getAgent();
        agent.attach(pid);
//        this.startInternal();
    }

    public static void main(String[] args) {

    }
}
