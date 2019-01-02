package ja.jvm.ch12;

import ja.Test;

/**
 * Created by totalyoung on 2018/10/18.
 */
public class VolatileTest {
    public static volatile int race=0;
    public static void increase(){
        race++;
    }
    private static final int THREADS_COUNT=20;
    public static void main(String[]args){
        Test t = new Test();
        Test t1 = t;
        Thread[]threads=new Thread[THREADS_COUNT];
        for(int i=0;i<THREADS_COUNT;i++){
            threads[i]=new Thread(new Runnable(){
                @Override
                public void run(){
                    for(int i=0;i<100000;i++){
                        increase();
//                        System.out.println(race);
                    }
                }
            });
            threads[i].start();

        }
//等待所有累加线程都结束
        while(Thread.activeCount()>1){
//            Thread.currentThread().getThreadGroup().list();
//            System.out.println(Thread.currentThread().getName());
            Thread.yield();
        }

        System.out.println(race);
    }
    
}
