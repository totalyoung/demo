package ja.jvm.ch4;

//import com.sun.xml.internal.fastinfoset.algorithm.HexadecimalEncodingAlgorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by yangst on 2018/8/15.
 */

public class JConsoleThreadTest {

    /**
     * 线程死循环演示
     */
    public static void createBusyThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true)//第41行
                    ;
            }
        }, "testBusyThread");
        thread.start();
    }

    /**
     * 线程锁等待演示
     */
    public static void createLockThread(final Object lock) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "testLockThread");
        thread.start();
    }

    /**
     * 线程锁唤醒演示
     */
    public static void createNotifyThread(final Object lock) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    lock.notify();
                }
            }
        }, "testLockThread");
        thread.start();
    }

    /**
     *线程死锁等待演示
     */
    static class SynAddRunalbe implements Runnable{
        int a,b;
        public SynAddRunalbe(int a,int b){
            this.a=a;
            this.b=b;
        }
        @Override
        public void run(){
            synchronized(Integer.valueOf(a)){
                synchronized(Integer.valueOf(b)){
                    System.out.println(a+b);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
//        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
//        String s = br.readLine();
//        System.out.println("readLine: "+s);
//        createBusyThread();
////        s=br.readLine();
////        System.out.println("readLine: "+s);
//        Object obj = new Object();
//        createLockThread(obj);
//        Thread.sleep(1000 * 20);
//        System.out.println("notify");
//        createNotifyThread(obj);
//        s=br.readLine();
//        System.out.println("readLine: "+s);

//        for(int i=0;i<1000;i++){
//            new Thread(new SynAddRunalbe(1,2)).start();
//            new Thread(new SynAddRunalbe(2,1)).start();
//        }
        Object bb = new Object();
        Object cc = new Object();
       int a =12;
       int b=2;
       a = a^b;
       b=a^b;
       a=a^b;
       System.out.println();


    }
}
