package ja.ClassLoaderDemo;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by totalyoung on 2018/11/25.
 */
public class DemoLoader extends ClassLoader {
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try (FileInputStream inputStream = new FileInputStream("F:\\littleGame\\MillionsOfProject\\Demo\\target\\classes\\ja\\Long.class")){
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            return defineClass(null, bytes, 0, bytes.length);
        }  catch (IOException e) {
            e.printStackTrace();

        }
        return super.findClass(name);
    }

//    @Override
//    public Class<?> loadClass(String name) throws ClassNotFoundException {
//        try (FileInputStream inputStream = new FileInputStream("F:\\littleGame\\MillionsOfProject\\Demo\\target\\classes\\java\\lang\\Long.class")){
//            byte[] bytes = new byte[inputStream.available()];
//            inputStream.read(bytes);
//            return defineClass(null, bytes, 0, bytes.length);
//        }  catch (IOException e) {
//            e.printStackTrace();
//
//        }
//        return super.loadClass(name);
//    }
}
