package ja.java8.testInterface;

/**
 * Created by yangst on 2018/7/25.
 */

public class D  implements B,A{

    public void hello(){
        B.super.hello();
    }
    public static void main(String[] args) {
        new D().hello();
    }
}
