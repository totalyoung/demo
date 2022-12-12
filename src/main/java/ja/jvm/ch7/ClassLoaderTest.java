package ja.jvm.ch7;

import sun.security.util.SecurityConstants;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yangst on 2018/9/19.
 */

public class ClassLoaderTest {
    public static void main(String[]args)throws Exception{
        ClassLoader myLoader=new ClassLoader(){
            @Override
            public Class<?>loadClass(String name)throws ClassNotFoundException{
                try{
                    String fileName=name.substring(name.lastIndexOf(".")+1)+".class";
                    InputStream is=getClass().getResourceAsStream(fileName);
                    if(is==null){
                        return super.loadClass(name);
                    }
                    byte[]b=new byte[is.available()];
                    is.read(b);
                    return defineClass(name,b,0,b.length);
                }catch(IOException e){
                    throw new ClassNotFoundException(name);
                }
            }
        };
        Object obj=myLoader.loadClass("ja.jvm.ch7.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof ClassLoaderTest);
    }

//    public ClassLoader getClassLoader(){
//        ClassLoader cl= getClassLoader0();
//        if(cl==null)
//        return null;
//        SecurityManager sm=System.getSecurityManager();
//        if(sm!=null){
//            ClassLoader ccl=ClassLoader.getCallerClassLoader();
//            if(ccl!=null&&ccl!=cl&&!cl.isAncestor(ccl)){
//                sm.checkPermission(SecurityConstants.GET_CLASSLOADER_PERMISSION);
//            }
//        }
//        return cl;
//    }
}
