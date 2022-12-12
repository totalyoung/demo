package ja; /**
 * Created by yangst on 2018/5/15.
 */

import java.math.BigInteger;

/**
 * @Description:
 * @Author yangst
 * @Date 2018/5/15
 **/
public class ExpensiveFunction implements Computable<String,BigInteger> {
    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        return new BigInteger(arg);
    }
}
