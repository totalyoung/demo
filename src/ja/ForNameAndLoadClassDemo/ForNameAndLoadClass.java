package ja.ForNameAndLoadClassDemo;

/**
 * Created by totalyoung on 2018/11/7.
 */
public class ForNameAndLoadClass {

    static{
        System.out.println("static block");
    }

    public static String str = getStr();

    public static String getStr(){
        System.out.println("static method");
        return "str";
    }
}
