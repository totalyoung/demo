/**
 * Created by yangst on 2018/10/11.
 */

public class InnerClassLoadDemo {
    static{
        System.out.println("outter");
    }

    static class StaticInnerClass{
        static {
            System.out.println("StaticInnerClass");
        }

    }

    class InnerClass{
        public final static int a = 1;
//        static {
//            System.out.println("InnerClass");
//        }
    }
    public static void main(String[] args) {

    }
}
