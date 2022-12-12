package ja.unsafeDemo;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.HashMap;

public class UnsafeDemo {

    public static UnsafeInstance create() {
        UnsafeInstance instance = null;
        try {
            instance = (UnsafeInstance) createUnsafe().allocateInstance(UnsafeInstance.class);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public static int changePrivate(){
        UnsafeInstance unsafeInstance = create();
        Field field = null;
        try {
            field = unsafeInstance.getClass().getDeclaredField("id");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        Unsafe unsafe = createUnsafe();
        unsafe.putInt(unsafeInstance, unsafe.objectFieldOffset(field), 1);
        return unsafeInstance.getId();

    }

    public static void main(String[] args) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put(1,1);
//        System.out.println(changePrivate());
    }

    public static Unsafe createUnsafe() {
        try {
            Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
            Field field = unsafeClass.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            Unsafe unsafe = (Unsafe) field.get(null);
            return unsafe;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

class UnsafeInstance{
    private  int id=2;

    static{
        System.out.println("UnsafeInstance static");
    }

    public UnsafeInstance() {
        System.out.println("UnsafeInstance");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}