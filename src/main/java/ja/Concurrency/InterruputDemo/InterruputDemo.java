package ja.Concurrency.InterruputDemo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by totalyoung on 2018/9/24.
 */
public class InterruputDemo {
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 500,
            TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(10));

    public static void test(){
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            executor.submit(()->{
                try {
                    Thread.sleep(1000*10);
                } catch (InterruptedException e) {
                    System.out.println("Thread: "+ finalI+"中断");
//                    e.printStackTrace();
                }
            });
        }

    }

    public static void test2(){
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            int finalI = i;
            long l = System.currentTimeMillis();
            Thread t = new Thread(()->{
//                System.out.println("start Thread: "+ finalI+" status: "+Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
                System.out.println("status: "+Thread.interrupted());
                System.out.println("start Thread: "+ finalI+" status: "+Thread.currentThread().isInterrupted());

                try {
                    System.out.println("aa");
                    Thread.sleep(1000*1);
                    long l1 = System.currentTimeMillis() - l;
//                    System.out.println("Thread: "+ finalI+"中断: "+l1+" status: "+Thread.interrupted());
//                    System.out.println("start Thread: "+ finalI+" status: "+Thread.currentThread().isInterrupted());
                } catch (InterruptedException e) {
                    long l1 = System.currentTimeMillis() - l;
                    System.out.println("Thread: "+ finalI+"中断: "+l1+" status: "+Thread.interrupted());
                }
            });
            list.add(t);
            t.start();
        }
        for (Thread thread : list) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void test1() throws InterruptedException {
        Thread t = new Thread(()->{
            while (true) {
                if(Thread.currentThread().isInterrupted()){
                    System.out.println("Interrupted");
                    break;
                }
                try {
                    Thread.sleep(1000*2);
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException InterruptStatus: "+Thread.currentThread().isInterrupted());
                    Thread.currentThread().interrupt();
                }
            }
        });
        t.start();
        Thread.sleep(1000);
        t.interrupt();
    }


    public static void main(String[] args) throws InterruptedException {

        test1();

    }
}
