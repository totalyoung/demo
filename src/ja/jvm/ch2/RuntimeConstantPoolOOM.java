package ja.jvm.ch2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by yangst on 2018/8/8.
 * VM args : -XX:PermSize=10M -XX:MaxPermSize=10M
 */

public class RuntimeConstantPoolOOM {

    public static void test1(){
        List<String> list = new ArrayList<>();
        int i = 0;
        while(true){
            list.add(String.valueOf(i++).intern());
        }
    }

    public static void test2(){
        String str1 = new StringBuilder("计算").append("软件").toString();
        System.out.println(str1.intern()==str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern()==str2);
    }


    public static void main(String[] args) {
        test2();
    }
}
