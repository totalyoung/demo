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
    public static void main(String[] args) {
        List<Thread> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            long l = System.currentTimeMillis();
            Thread t = new Thread(()->{
                System.out.println("start Thread: "+ finalI);
                try {
                    Thread.sleep(1000*10);
                } catch (InterruptedException e) {
                    long l1 = System.currentTimeMillis() - l;
                    System.out.println("Thread: "+ finalI+"中断: "+l1);
                }
            });
            list.add(t);
            t.start();
        }
        for (Thread thread : list) {
            thread.interrupt();
        }
//        Thread.currentThread().interrupt();
//        BlockingQueue<Runnable> queue = executor.getQueue();
//        test();


    }
}
