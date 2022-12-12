package ja.jvm.ch8;

/**
 * Created by yangst on 2018/9/28.
 */

public class StaticDispatch {
    static abstract class Human{
    }
    static class Man extends Human{
    }
    static class Woman extends Human{
    }
    public void sayHello(Human guy){
        System.out.println("hello,guy！");
    }
    public void sayHello(Man guy){
        System.out.println("hello,gentleman！");
    }
    public void sayHello(Woman guy){
        System.out.println("hello,lady！");
    }
    public static void main(String[]args){
        Human man=new Man();
        Human woman=new Woman();
        StaticDispatch sr=new StaticDispatch();
        sr.sayHello(man);
        sr.sayHello(woman);
    }
}
