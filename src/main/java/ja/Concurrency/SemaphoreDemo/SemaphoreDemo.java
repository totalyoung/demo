package ja.Concurrency.SemaphoreDemo;

import java.util.concurrent.Semaphore;

/**
 * Created by admin on 2020/11/9.
 */
public class SemaphoreDemo {

    private volatile Semaphore semaphore = new Semaphore(5);
    boolean a = false;
    public void test(){
        for (int i = 0; i <5 ; i++) {
            final int a = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire(1);
                        long l = System.currentTimeMillis();
                        while (System.currentTimeMillis()-l<2000){
                        }
                        //具体业务逻辑
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        semaphore.release(1);
                    }


                }
            }).start();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SemaphoreDemo  c = new SemaphoreDemo();
//        c.a =true;
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
//                while (true) {
                synchronized (c) {
                    for (int i = 10; i <20 ; i++) {
                        c.notifyAll();
                        try {
                            c.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(i);
                        c.notifyAll();
                    }
//                        while(!c.a){
//                            System.out.println("a");
//                            try {
//                                c.wait();
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                            c.a = false;
//                            c.notifyAll();

                    }
                }
//            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
//                while (true) {
                synchronized (c) {
                    t1.start();
                    for (int i = 0; i <10 ; i++) {
                        c.notifyAll();
                        try {
                            c.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(i);
                        c.notifyAll();
                    }
//                    while (c.a) {
//                        System.out.println("1");
//                        try {
//                            c.wait();
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                        c.a=true;
//                        c.notifyAll();
                    }
                }
//            }
        });
//
//        SemaphoreDemo semaphoreDemo = new SemaphoreDemo();
//        semaphoreDemo.test();
        t2.start();
//        t1.join();

    }


}
