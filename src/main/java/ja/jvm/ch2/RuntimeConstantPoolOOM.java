package ja.jvm.ch2;

import org.omg.SendingContext.RunTime;

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
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern()==str2);

        String str1 = new StringBuilder("计算").append("软件").toString();
        System.out.println(str1.intern()==str1);
//        System.out.println(str1.intern()==str1);
    }


    public static void main(String[] args) {
//        //编译后，new StringBuilder().append("He").append("llo").toString();
//        // 堆上创建"He","llo"，"hello",s3实例,但是字符串常量池中只存放"He","llo","hello"实例的引用
//        String s3 = new String("He") + new String("llo");
//        //将堆上"Hello"的引用存入字符串常量池中，再返回该引用
//        s3.intern();
//        // 获取字符串常量池中的"Hello"的引用
//        String s4 = "Hello";
//
//        //true，如果不执行s3.intern()，将返回false
//        System.out.println(s3 == s4);// true
        String s3 = new String("He") + new String("llo");
        String s4 = "Hello";
        s3.intern();
        System.out.println(s3 == s4);

    }
}
