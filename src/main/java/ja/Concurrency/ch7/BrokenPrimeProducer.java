package ja.Concurrency.ch7;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

/**
 * Created by totalyoung on 2018/9/24.
 */
public class BrokenPrimeProducer extends Thread{

    private final BlockingQueue<BigInteger> queue;
    private volatile boolean cancelled = false;

    public BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
        this.queue = queue;
    }

    public void run(){
        BigInteger p = BigInteger.ONE;
        while(!cancelled){
            try {
                queue.put(p=p.nextProbablePrime());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void cancel(){
        cancelled =true;
    }

    void consumePrime() throws InterruptedException {
        this.start();
        while(needMorePrime()){
            try{
                queue.take();
            }finally {
                cancel();
            }
        }
    }

    public boolean needMorePrime(){
        return true;
    }
}
