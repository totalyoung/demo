package ja.Concurrency.ReentrantLockDemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by totalyoung on 2018/10/23.
 */
public class LockDemo implements Runnable{

    private static Lock lock = new ReentrantLock();
    private static int index ;

    @Override
    public void run() {
//        lockTest();
        try {
            tryLockTest();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+": 已中断获取锁！");
            e.printStackTrace();
        }
    }

    public void lockInterruptiblyTest() throws InterruptedException {
        //一直阻塞到有空余的锁，但接受当前线程的中断信号
        lock.lockInterruptibly();
        try{
            System.out.println(Thread.currentThread().getName()+": 已获取到锁！");
            Thread.sleep(3000);
        }finally {
            lock.unlock();
        }
    }

    public void lockTest(){
        //一直阻塞到有空余的锁
        lock.lock();
        try{
            for (int i = 0; i <100 ; i++) {
                System.out.println(Thread.currentThread().getName()+": "+index++);
            }
        }finally {
            lock.unlock();
        }
    }

    public void tryLockTest() throws InterruptedException {
        long startTime = System.currentTimeMillis();
        while(true){
            //尝试获取锁，没有参数：立刻返回获取状态 有参数：在规定的时间，轮询获取锁，直到时间结束返回状态
           if(/*lock.tryLock(10, TimeUnit.SECONDS)*/lock.tryLock()){
               try{
                   for (int i = 0; i <100 ; i++) {
                       System.out.println(Thread.currentThread().getName()+": "+index++);
                   }
                   Thread.sleep(15000);
                   return;
               }finally {
                   lock.unlock();
               }
           }else{
               System.out.println(Thread.currentThread().getName()+": 获取不到锁！");
               long endTime = System.currentTimeMillis();
               if(endTime-startTime >10000){
                   System.out.println("endTime-startTime: " +(endTime-startTime));
                   return;
               }
           }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new LockDemo());
        Thread t2 = new Thread(new LockDemo());
        t1.start();
        t2.start();
        Thread.sleep(1000);

//        t2.interrupt();

    }
}
