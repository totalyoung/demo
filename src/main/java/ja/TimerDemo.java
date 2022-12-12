package ja;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by admin on 2020/10/26.
 */
public class TimerDemo {

    public static void main(String[] args) {

        test1();
    }

    public static void test1() {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        final long al = System.currentTimeMillis();
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);
        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                long l = System.currentTimeMillis();
                int i = atomicInteger.incrementAndGet();
//                int time = 100;
//                if(i%2==0) time=1000;
//                while (System.currentTimeMillis() - l < time) {
//                }

                System.out.println(Thread.currentThread().getId()+":"+i + ": " + (System.currentTimeMillis() - al)+" a: 1");
            }
        }, 0, 200, TimeUnit.MILLISECONDS);

        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                long l = System.currentTimeMillis();
                int i = atomicInteger.incrementAndGet();
//                int time = 100;
//                if(i%2==0) time=1000;
//                while (System.currentTimeMillis() - l < time) {
//                }

                System.out.println(Thread.currentThread().getId()+":"+i + ": " + (System.currentTimeMillis() - al)+" a: 2");
            }
        }, 0, 300, TimeUnit.MILLISECONDS);

    }


    public static void test(){
        Timer timer = new Timer();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        final long al = System.currentTimeMillis();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                int time = -1;
                int i = atomicInteger.incrementAndGet();
                if(i==4) time = 400;
                long l = System.currentTimeMillis();
                while (System.currentTimeMillis()-l<time){
                }

                System.out.println(i+": "+(System.currentTimeMillis()-al)+":"+(System.currentTimeMillis()-l));
            }
        },1000,200);
    }
}
