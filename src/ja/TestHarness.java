package ja; /**
 * Created by yangst on 2018/5/14.
 */

import java.util.concurrent.CountDownLatch;

/**
 * @Description:
 * @Author yangst
 * @Date 2018/5/14
 **/
public class TestHarness {

    public long timeTasks(int nThreads,final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for(int i = 0; i<nThreads;i++){
            Thread t = new Thread(){
                @Override
                public void run() {
                    try{
                        startGate.await();
                        try{
                            task.run();
                        }finally {
                            endGate.countDown();
                        }
                    }catch (InterruptedException e){

                    }
                }
            };
            t.start();
        }
        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end-start;
    }

    public static void main(String[] args) throws InterruptedException {
        Test test = new Test(1,2);
//        new TestHarness().timeTasks(10,new Test(1,2));

    }
}
