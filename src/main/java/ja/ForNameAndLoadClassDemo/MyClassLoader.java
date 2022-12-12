package ja.ForNameAndLoadClassDemo;

/**
 * Created by totalyoung on 2018/11/7.
 */
public class MyClassLoader extends ClassLoader {

    public Class<?> getClassLoader(String name) throws ClassNotFoundException {
        return loadClass(name,true);
    }
}
