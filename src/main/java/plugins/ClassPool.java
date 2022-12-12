package plugins;


import java.io.*;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;
import java.util.HashMap;
import java.util.Map;

public class ClassPool implements ClassFileTransformer {

    private Map<Class,byte[]> pool = new HashMap<>();

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        if(className.indexOf("java")==0||className.indexOf("javax")==0){
            return classfileBuffer;
        }
        String filePath = "";
        File file = new File(filePath);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            fileOutputStream.write(classfileBuffer);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.put(classBeingRedefined,classfileBuffer);
        return classfileBuffer;
    }

    public static void main(String[] args) throws IOException {
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("\\plugins\\ClassPool.class");
        String filePath = "C:\\Users\\total\\Desktop\\ClassPool.class";
        File file = new File(filePath);
        FileOutputStream fileOutputStream=null;
        try {
            byte[] b = new byte[1024];
            fileOutputStream = new FileOutputStream(filePath);
            while(resourceAsStream.read(b)>0){
                fileOutputStream.write(b);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            resourceAsStream.close();
            fileOutputStream.close();
        }
    }
}
