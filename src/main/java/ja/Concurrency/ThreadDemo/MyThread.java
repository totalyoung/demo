package ja.Concurrency.ThreadDemo;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by admin on 2020/11/14.
 */
public class MyThread extends Thread {
    volatile boolean stop = false;
    private static final Object s_lockobj = new Object();
    static{
        System.out.println("aaa");
    }
    {
        System.out.println("bbb");
    }

    public MyThread(boolean stop) {
        System.out.println("ccc");
        this.stop = stop;
    }

    @Override
    public void run() {

        while(true){
            synchronized (s_lockobj){
                //处理逻辑
                stop=true;
            }
            if(stop){
                break;
            }
        }

    }

    public static void main(String[] args) {
        System.out.println("hellp");
        new MyThread(false);
    }

}
