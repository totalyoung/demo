package ja;


/**
 * Created by yangst on 2018/8/6.
 */

public class SubText extends Test{

    @Override
    public void testExtends(){
        System.out.println("Subtest");
    }

    public static void main(String[] args) {
        Test t = new Test();
        t.testExtends();
        t = new SubText();
        t.testExtends();
    }

}
