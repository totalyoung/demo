package ja.Concurrency.ch10;

import java.util.Random;

/**
 * Created by totalyoung on 2018/10/10.
 */
public class DemonstrateDeadlock {

    public static final int NUM_THREADS = 5;
    public static final int NUM_ACCOUNTS = 4;
    public static final int NUM_ITERATIONS = 1000000;

    private static final String tieLock = "tieLock";

    static void transferMoney(final String from,final String to) throws InterruptedException {
        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);

        if(fromHash < toHash){
            synchronized (from){
                synchronized (to){
                    System.out.println(Thread.currentThread().getName()+": "+from +" < " +to);
                    Thread.sleep(3000);
                }
            }
        }else if(fromHash > toHash) {
            synchronized (to) {
                synchronized (from) {
                    System.out.println(Thread.currentThread().getName()+": "+from +" > " +to);
//                    Thread.sleep(5000);
                }
            }
        }else{
            synchronized (tieLock){
                synchronized (from) {
                    synchronized (to) {
                        System.out.println(Thread.currentThread().getName()+": "+from +" = " +to);
                        Thread.sleep(5000);
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        final Random r = new Random();
        final String[] strings = new String[NUM_ACCOUNTS];


        for (int i = 0; i <strings.length ; i++) {
            strings[i] = "a_"+i;
        }

        class TransferThread extends Thread{
            public void run(){
                System.out.println(Thread.currentThread().getName()+" start");
                for (int i = 0; i <NUM_ITERATIONS ; i++) {
                    int from = r.nextInt(NUM_ACCOUNTS);
                    int to = NUM_ACCOUNTS-1-from;
                    try {
                        transferMoney(strings[from],strings[to]);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        }
        for (int i = 0; i <NUM_THREADS ; i++) {
            new TransferThread().start();
        }
    }
}
