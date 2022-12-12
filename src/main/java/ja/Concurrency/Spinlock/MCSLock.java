package ja.Concurrency.Spinlock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by admin on 2020/11/6.
 */
public class MCSLock {

    private final AtomicReference<Node> queue = new AtomicReference<>();
    private final ThreadLocal<Node> curr = new ThreadLocal<>();


    public void lock(int i){
        Node node = new Node();
        curr.set(node);
        Node myPred = queue.getAndSet(node);

        if(myPred!=null){
            //前继不为空，前继获取了锁，
            myPred.next = node;
            //需要等待,直到前继节点把当前节点状态设为false
            while (node.isWaiting){
            }
        }
        //前继为空，不需要等待
        System.out.println("lock："+ i+" time: "+System.currentTimeMillis());

    }

    public void unLock(int i){
        Node myCurr = curr.get();
        if(myCurr==null) return;

        if(myCurr.next!=null){
            //后继不为空，需要通知其状态为false
            myCurr.next.isWaiting=false;
            //并把当前节点后继置为null，代表出列
            myCurr.next = null;
        }else{
            //后继为空，则当前节点是最后一个，尝试把queue置为null
            if(queue.compareAndSet(myCurr,null)){
//                return;
            }else{
                //如果失败，则表示当前节点不是最后一个，新增了节点，表示有后继节点（其他线程）在等待锁
                //需要等待后继节点不为空
                while(myCurr.next ==null){
                    System.out.println();
                }
            }
        }
        System.out.println("unlock："+ i+" time: "+System.currentTimeMillis());
    }

    class Node{
        volatile Node next;
        volatile boolean isWaiting = true;
    }

    public static void main(String[] args) {
        MCSLock mcsLock = new MCSLock();
        for (int i = 1; i <= 50; i++) {
            final int a =i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    mcsLock.lock(a);
                    long l = System.currentTimeMillis();
                    while (System.currentTimeMillis()-l<100){

                    }
                    mcsLock.unLock(a);
                }
            }).start();
        }

    }

}
