package ja.Concurrency.ch7;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by totalyoung on 2018/9/22.
 */

public class PrimeGenerator implements Runnable{
    private List<BigInteger> prime = new ArrayList<>();
    private volatile boolean cancelled;
    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while(!cancelled){
            p=p.nextProbablePrime();
            synchronized (this){
                prime.add(p);
            }
        }
    }

    public void cancel(){
        cancelled = true;
    }

    public synchronized List<BigInteger> get(){
        return new ArrayList<>(prime);
    }

    List<BigInteger> aSecondOfPrimes() throws InterruptedException {
        new Thread(this).start();
        try{
            SECONDS.sleep(1);
        }finally {
            cancel();
        }
        return get();
    }

    public static void main(String[] args) throws InterruptedException {
        PrimeGenerator primeGenerator = new PrimeGenerator();
        List<BigInteger> bigIntegers = primeGenerator.aSecondOfPrimes();
        System.out.println(bigIntegers);
    }
}
