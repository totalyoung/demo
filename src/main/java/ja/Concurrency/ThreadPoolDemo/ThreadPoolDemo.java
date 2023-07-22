package ja.Concurrency.ThreadPoolDemo;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2020/11/17.
 */
public class ThreadPoolDemo {

    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(5,5,10, TimeUnit.SECONDS,new LinkedBlockingQueue<>());

    public static void main(String[] args) {
        for (int i = 0; i <10 ; i++) {
            final int b = i;
            pool.execute(new Runnable() {
                @Override
                public void run() {
//                    try {
//                        Thread.sleep(2000);
//
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    System.out.println(100/b);
                }
            });

        }
//        while(true){
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("ActiveCount: "+pool.getActiveCount());
//            System.out.println("PoolSize: "+pool.getPoolSize());
//        }
    }
}
