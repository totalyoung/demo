package ja.Concurrency.countDownLatchDemo;

import java.util.concurrent.CountDownLatch;

/**
 * Created by admin on 2020/11/13.
 */
public class CountDownLatchDemo {

    static final CountDownLatch countDownLatch = new CountDownLatch(3);


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i <3 ; i++) {
            final int a = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while(a+1!=countDownLatch.getCount()){

                    }
//                    long l = System.currentTimeMillis();
//                    //模拟业务逻辑处理时间
//                    while (System.currentTimeMillis()-l<2000){
//                    }
                    System.out.println(a);
                    countDownLatch.countDown();

                }
            }).start();
        }

        countDownLatch.await();
    }
}
