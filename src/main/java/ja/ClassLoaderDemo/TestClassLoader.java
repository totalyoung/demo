package ja.ClassLoaderDemo;

import ja.Long;
//import org.junit.jupiter.api.Test;

/**
 * Created by totalyoung on 2018/11/25.
 */
public class TestClassLoader {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        DemoLoader demoLoader = new DemoLoader();
        System.out.println(demoLoader.getParent());
        Class<?> aClass = demoLoader.loadClass("Mine");
        System.out.println(aClass);
        DemoLoader demoLoader2 = new DemoLoader();
        Class<?> aClass2 = demoLoader2.loadClass("Mine");
        System.out.println(demoLoader2.getParent());
        System.out.println(aClass2);

//        Long l = new Long();
    }

//    @Test
    public void testContextClassLoaser() throws ClassNotFoundException {
        DemoLoader demoLoader = new DemoLoader();
        Class<?> aClass = demoLoader.loadClass("E:\\ideaSet\\CleanMine\\out\\production\\CleanMine\\Mine");
        System.out.println(aClass);
        DemoLoader demoLoader2 = new DemoLoader();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setContextClassLoader(demoLoader2);
                ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
                Class<?> aClass2 = null;
                try {
                    aClass2 = contextClassLoader.loadClass("E:\\ideaSet\\CleanMine\\out\\production\\CleanMine\\Mine");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                System.out.println(aClass2);
                System.out.println(contextClassLoader.toString());
            }
        }).start();

    }
}
