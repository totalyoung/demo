package ja.Concurrency;

import org.junit.After;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

public class ABADemo {

    public static AtomicReference<Integer> money = new AtomicReference<>();
    static{
        money.set(19);
    }

    @Test
    public void test(){
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
               while (true){
                   Integer integer = money.get();
                   if(integer<20){
                       if(money.compareAndSet(integer, integer+20)){
                           System.out.println("充值成功："+money.get());
                           break;
                       }
                   }else{
//                       System.out.println("大于20");
                       break;
                   }
               }
            }).start();
        }

        new Thread(()->{
            for (int i = 0; i < 100; i++) {
                while (true){
                    Integer integer = money.get();
                    if(integer>10){
                        System.out.println("大于10");
                        if(money.compareAndSet(integer, integer-10)){
                            System.out.println("消费10，"+money.get());
                            break;
                        }
                    }else{
                        System.out.println("没有");
                        break;
                    }
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @After
    public void after(){
        while (true);
    }
}
