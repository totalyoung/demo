package ja; /**
 * Created by yangst on 2018/5/15.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author yangst
 * @Date 2018/5/15
 **/
public class Memoizerl<A,V> implements Computable<A,V> {

    private final Map<A,V> cache = new HashMap<>();
    private final Computable<A,V> c;

    public Memoizerl(Computable<A, V> c) {
        this.c = c;
    }

    @Override
    public synchronized V compute(A arg) throws InterruptedException {
       V result = cache.get(arg);
       if(result == null){
           result = c.compute(arg);
           cache.put(arg,result);
       }
       return result;
    }
}
