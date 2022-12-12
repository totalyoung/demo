package ja.Concurrency.SynchronizedDemo;

/**
 * Created by totalyoung on 2018/10/23.
 */
public class SynchronizedDemo implements Runnable{

    public static synchronized void staticSynchronized(){
        System.out.println(Thread.currentThread().getName()+": staticSynchronized");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void notStaticSynchronized(){
        System.out.println(Thread.currentThread().getName()+": notStaticSynchronized");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        notStaticSynchronized();
        staticSynchronized();



    }

    public static void main(String[] args) {
//        SynchronizedDemo demo = new SynchronizedDemo();
        Thread t1 = new Thread(new SynchronizedDemo());
        Thread t2 = new Thread(new SynchronizedDemo());
        t1.start();
        t2.start();
    }
}
