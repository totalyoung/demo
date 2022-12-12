package ja.jvm.ch7;

/**
 * Created by yangst on 2018/9/19.
 */

public class TestClass extends TestClass1 {
    public TestClass() {
        System.out.println(this.getClass().getName());
    }
}
