package ja.Concurrency.LockDemo;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by admin on 2020/10/31.
 */
public class LockDemo {

    Lock lock =new ReentrantLock(true);
    AtomicInteger i = new AtomicInteger(0);


    public void test(int i) throws InterruptedException {
        System.out.println("test");
        lock.lock();
        int i1 = 10;
        if(i%2==0) i1 = 1000;

        System.out.println(i);
        lock.unlock();
    }

    public void testLock() throws InterruptedException {

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                System.out.println("testLock");
                long l = System.currentTimeMillis();
                while (System.currentTimeMillis()-l<3000){

                }
                lock.unlock();
                System.out.println("testLock2");
            }
        }).start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                lock.lock();
//                System.out.println("testLock");
//                long l = System.currentTimeMillis();
//                while (System.currentTimeMillis()-l<3000){
//
//                }
//                lock.unlock();
//                System.out.println("testLock2");
//            }
//        }).start();
        long l = System.currentTimeMillis();
        while (System.currentTimeMillis()-l<2000){
        }
        test(1);
    }

    public void test1(){
        LockDemo lockDemo = new LockDemo();
        for (int i = 0; i <100 ; i++) {
            final int a = i;

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        lockDemo.test(a);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        }
    }

    public void testWait(){

    }


    public  static void main(String[] args) throws InterruptedException {
//        LockDemo lockDemo = new LockDemo();
//        lockDemo.testLock();

        System.out.println(Integer.toHexString(131072));


    }

}
