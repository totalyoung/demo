package ja.Concurrency.Spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by admin on 2020/11/5.
 */
public class CLHLock {


    private final AtomicReference<Node> tail = new AtomicReference<>(new Node());
    private final ThreadLocal<Node> pred = new ThreadLocal<>();
    private final ThreadLocal<Node> curr = new ThreadLocal<>();


    public void lock(int i){
        Node node = new Node();
        node.islocked=true;
        curr.set(node);
        Node myPred = tail.getAndSet(node);
        pred.set(myPred);
        if(myPred!=null){
            while(myPred.islocked){

            }
        }

    }

    public void unLock(int i){
        Node node = curr.get();
        node.islocked = false;
        curr.set(pred.get());
        node=null;
        System.out.println("unLock: "+i+" time: "+System.currentTimeMillis());

    }

    static class Node{
        private volatile boolean islocked;

    }

    public static void main(String[] args) {
        CLHLock clhLock = new CLHLock();
        clhLock.curr.set(new Node());
        clhLock.curr.set(new Node());
//        int a =1;
//        clhLock.lock(a);
//        if(a==0){
//            long l = System.currentTimeMillis();
//            while (System.currentTimeMillis()-l<5000){
//
//            }
//        }
//        System.out.println(a);
//        clhLock.unLock(a);
//        for (int i = 1; i <= 50; i++) {
//            final int a =i;
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    clhLock.lock(a);
////                    if(a==0){
//                        long l = System.currentTimeMillis();
//                        while (System.currentTimeMillis()-l<500){
//
//                    }
////                    }
////                    System.out.println(a);
//                    clhLock.unLock(a);
//                    clhLock.lock(a*100);
//                    l=System.currentTimeMillis();
//                    while (System.currentTimeMillis()-l<500){
//
//                    }
//                    clhLock.unLock(a*100);
//                }
//            }).start();
//            if(a==0){
//                long l = System.currentTimeMillis();
//                while (System.currentTimeMillis()-l<500){
//
//                }
//            }


//        }
    }
}
