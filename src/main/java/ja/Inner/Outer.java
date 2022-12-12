package ja.Inner;

/**
 * Created by admin on 2020/8/11.
 */
public class Outer {

    private int outA;

    private int outB;

    static final class InnerOne{
        private int innerA;

    }

    static final class InnerTwo{
        private int innerB;

        private InnerOne innerOne;
        public void test(){
            innerB = innerOne.innerA;
        }
    }
}
