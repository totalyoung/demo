package ja;

/**
 * Created by yangst on 2018/5/15.
 */
public interface Computable<A,V> {
    V compute(A arg) throws  InterruptedException;
}
