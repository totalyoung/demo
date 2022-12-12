package ja.Concurrency.ioDemo;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by totalyoung on 2018/9/22.
 */
public class Test {

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(0,1,5L, TimeUnit.SECONDS,new LinkedBlockingQueue<>(1));
        for (int i = 0; i < 7; i++) {
            final int a = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(a);
                    try {
                        Thread.sleep(10000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            if(i>2){
                try {
                    Thread.sleep(10000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
//        AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
//        int i = ctl.get();
//        ctl.compareAndSet(i,i+1);
//        i = ctl.get();
//        System.out.println(Integer.toBinaryString(i));
//        System.out.println(Integer.toBinaryString(CAPACITY));
//
//        System.out.println( workerCountOf(ctl.get()));
//
//        System.out.println(Integer.toBinaryString(-1 << COUNT_BITS));
    }
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int RUNNING    = -1 << COUNT_BITS;
    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));

    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }


    public static void testSwap(){
        Integer a = new Integer(1);
        Integer b = new Integer(2);
        swap(a,b);
        System.out.println(a);
        System.out.println(b);

        System.out.println(Integer.toHexString(-889275714));
    }

    public static void swap(Integer a ,Integer b){
        Integer temp;
        temp =a;
        a=b;
        b=temp;
        System.out.println(a);
        System.out.println(b);
    }



}
