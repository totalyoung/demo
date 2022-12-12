package ja;

/**
 * Created by yangst on 2018/9/20.
 */

public class NotInitialization {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        System.out.println(SubClass.value);
//        System.out.println(Class.forName("ja.SSClass"));
        System.out.println(SSClass.class.newInstance());
    }
}

 class SSClass
{
    static
    {
        System.out.println("SSClass");
        System.out.println(SSClass.class.getName());
    }
}
 class SuperClass extends SSClass
{
    static
    {
        System.out.println("SuperClass init!");
    }

    public static int value = 123;

    public SuperClass()
    {
        System.out.println("init SuperClass");
    }
}
 class SubClass extends SuperClass
{
    static
    {
        System.out.println("SubClass init");
    }

    static int a;

    public SubClass()
    {
        System.out.println("init SubClass");
    }
}