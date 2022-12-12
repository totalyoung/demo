package plugins;


import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;
import com.sun.tools.attach.spi.AttachProvider;
import sun.tools.attach.HotSpotVirtualMachine;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class TestClass {

    public static void main(String[] args) throws IOException, AttachNotSupportedException {
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        HotSpotVirtualMachine attach = (HotSpotVirtualMachine)VirtualMachine.attach("");
//        attach.
//        ClassPool classPool = new ClassPool();
//        while(true){
//            Scanner scanner = new Scanner(System.in);
//            System.out.println(scanner.next());
//        }
        for (VirtualMachineDescriptor virtualMachineDescriptor : list) {
            AttachProvider provider = virtualMachineDescriptor.provider();
            System.out.println(virtualMachineDescriptor.displayName());
        }
    }
}
