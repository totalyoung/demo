package ja.jvm.ch4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangst on 2018/8/14.
 */

public class JConsoleTest {
    static class OOMObject{
        public byte[] placeholder = new byte[64*1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for(int i = 0 ;i<num;i++){
            Thread.sleep(50);
            list.add(new OOMObject());
        }
        System.gc();
    }

    public static void main(String[] args) throws InterruptedException {
        fillHeap(1000);
    }
}


