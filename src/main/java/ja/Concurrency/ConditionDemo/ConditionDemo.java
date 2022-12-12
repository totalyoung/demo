package ja.Concurrency.ConditionDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by admin on 2020/11/17.
 */
public class ConditionDemo {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        ThreadGroup threadGroup = new ThreadGroup("ConditionDemo");
        for (int i = 0; i <10 ; i++) {
            new Thread(threadGroup,new Runnable() {
                @Override
                public void run() {
                    reentrantLock.lock();
                    try {
                        System.out.println();
                        condition.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    reentrantLock.unlock();
                }
            },"t"+i).start();
        }
        for (int i = 0; i <10 ; i++) {
            reentrantLock.lock();
            System.out.println("huanxing");
            condition.signal();
            reentrantLock.unlock();
        }
    }
}
