package ja.jvm.ch7;

import ja.jvm.ch6.TestClass;

/**
 * Created by yangst on 2018/9/19.
 */

public class TestClass1 extends TestClass{
    public TestClass1() {
        System.out.println(this.getClass().getName());
    }
}
