package ja.Concurrency.ch5;

import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * Created by totalyoung on 2018/8/19.
 */
public class BoundeHashSet<T> {
    private final Set<T> set;
    private final Semaphore sem;

    public BoundeHashSet(Set<T> set, Semaphore sem) {
        this.set = set;
        this.sem = sem;
    }

    public boolean add(T t) throws InterruptedException {
        sem.acquire();
        boolean wasAdd = false;
        try{
            wasAdd = set.add(t);
            return wasAdd;
        }finally {
            if(!wasAdd)
                sem.release();
        }
    }

    public boolean remove(Object o){
        boolean wasRemoved = set.remove(o);
        if(wasRemoved)
            sem.release();
        return wasRemoved;
    }
}
