package ja.ForNameAndLoadClassDemo;

import org.apache.tools.ant.taskdefs.Classloader;
import sun.reflect.Reflection;

/**
 * Created by totalyoung on 2018/11/7.
 */
public class ForNameAndLoadClassTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
//        ForNameAndLoadClassTest.class.getClassLoader();
//        ClassLoader.getSystemClassLoader();
        ClassLoader contextClassLoader = new MyClassLoader();
        Class<?> forNameAndLoadClass = contextClassLoader.loadClass("ja.ForNameAndLoadClassDemo.ForNameAndLoadClass");
        forNameAndLoadClass.newInstance();
        System.out.println("-----------------------------------");
        Class<?> forNameAndLoadClass1 = Class.forName("ja.ForNameAndLoadClassDemo.ForNameAndLoadClass");
    }
}
