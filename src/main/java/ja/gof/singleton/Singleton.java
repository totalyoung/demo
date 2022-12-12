package ja.gof.singleton;

import java.lang.reflect.Constructor;

public class Singleton {
    private final static Singleton singleton = new Singleton();
    private Singleton() {

    }
    public static Singleton getInstance(){
        return singleton;
    }

    public void test(){
        System.out.println("aa");
    }

    public static void main(String[] args) throws Exception {
        Class<Singleton> singletonClass = Singleton.class;
        Constructor<Singleton> constructor = singletonClass.getDeclaredConstructor(null);
        constructor.setAccessible(true);
        Singleton singleton = constructor.newInstance(null);
        singleton.test();
    }
}
