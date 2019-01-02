package ja;

import java.util.Random;

/**
 * Created by yangst on 2018/10/10.
 */

public class RunnableDome implements Runnable {
    @Override
    public void run() {
        test1();
    }

    public void test1(){
        Random r = new Random();
        while(true){
            int i = r.nextInt(100);
            if(i==20){
                System.out.println(Thread.currentThread().getName());
                throw  new RuntimeException();
            }
        }
    }
}
