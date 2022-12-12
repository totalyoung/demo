package ja;


import sun.misc.Unsafe;
import sun.misc.VM;

import java.lang.reflect.Field;

/**
 * Created by yangst on 2018/9/20.
 */

public class Long {

    private int i ;

    public void setI(int i) {
        this.i = i;
    }

    public Long() {
        System.out.println("Long");
    }


    public static void main(String[] args) throws Exception {
        String str1 = new String("11");
        String str2 = new String("11");
        System.out.println(str1.intern() == str2.intern());
        System.out.println(System.identityHashCode(str1));
        System.out.println(System.identityHashCode(str2));
        System.out.println(System.identityHashCode(str1.intern()));
        System.out.println(System.identityHashCode(str2.intern()));

        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);

    }
}
