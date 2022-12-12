package ja.Concurrency.ch5;

import java.util.concurrent.CountDownLatch;

/**
 * Created by totalyoung on 2018/8/12.
 */
public class TestHarness {

    public static long timeTask(int nThreads,final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for(int i = 0; i<nThreads;i++){
            Thread t = new Thread(()->{
                try {
                    startGate.await();//将一直阻塞到startGate计数器等于0；
                    try{
                        task.run();
                    }finally {
                        endGate.countDown();
                    }

                } catch (InterruptedException e) {
                }
            });
            t.start();
        }
        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        System.out.println("startGate await");
        startGate.await();
        long end = System.nanoTime();
        return end -start;
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = ()->{
            long start = System.nanoTime();
//            for(int i =0;i<10;i++){
                try {
//                    System.out.println(Thread.currentThread().getName()+"_"+i);
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//            }
            long end = System.nanoTime();
            System.out.println(Thread.currentThread().getName()+": "+(end-start));
        };
        System.out.println(timeTask(10,runnable));
    }
}
