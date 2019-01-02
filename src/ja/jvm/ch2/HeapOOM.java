package ja.jvm.ch2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangst on 2018/8/7.
 * VM args：-Xmx20M -Xms20M
 */

public class HeapOOM {

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while(true){
            list.add(new OOMObject());
        }
    }
}

class OOMObject{
    public static void main(String[] args) {
        System.out.println("OOMObject");
    }
}