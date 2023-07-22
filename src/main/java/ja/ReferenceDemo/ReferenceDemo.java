package ja.ReferenceDemo;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

public class ReferenceDemo {

    public static void main(String[] args) throws InterruptedException {
        String str = new String("aaa");
        String str2 = new String("aaa");
        String str3 = new String("bbb");
        ReferenceQueue<String> referenceQueue = new ReferenceQueue<>();
//        SoftReference<String> weakRef2 = new SoftReference<>(str2, referenceQueue);
        //弱引用在垃圾回收之后，str2对象被回收；
        WeakReference<String> weakRef = new WeakReference<>(str, referenceQueue);
        //虚引用在垃圾回收之后，str3对象没有被回收，还存在PhantomReference之中，需要认为clear（）；
        PhantomReference<String> phantomReference = new PhantomReference<>(str3,referenceQueue);
        str=null;
        str2=null;
        str3=null;
        System.gc();
//        weakRef.clear();
//        Reference<? extends String> poll = referenceQueue.poll();
//        String s = poll.get();
//        System.out.println();
        Thread.sleep(1000);
        System.gc();
        System.out.println();
    }
}
