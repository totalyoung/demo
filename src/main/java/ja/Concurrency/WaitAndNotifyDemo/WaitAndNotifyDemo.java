package ja.Concurrency.WaitAndNotifyDemo;

/**
 * Created by admin on 2020/11/14.
 */
public class WaitAndNotifyDemo {

    private static final Object s_lockobj = new Object();

    public static void test() throws InterruptedException {
        for (int i = 0; i <50 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (s_lockobj){
                        try {
                            System.out.println(Thread.currentThread().getId()+" wait : "+System.nanoTime());
                            s_lockobj.wait();
                            System.out.println(Thread.currentThread().getId()+" notify : "+System.nanoTime());
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
//            Thread.sleep(200);
        }
        Thread.sleep(1000);
        for (int i = 0; i <50 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (s_lockobj){
                        s_lockobj.notify();
                    }
                }
            }).start();
            Thread.sleep(200);
        }
    }

    public static void myWait(){

    }

    public static void myNotify(){
        synchronized (s_lockobj){

//            System.out.println(Thread.currentThread().getId()+" notify : "+System.currentTimeMillis());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        test();
    }
}
