package ja.Concurrency.test;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created by totalyoung on 2018/10/9.
 */
public class ConcurrencyExceptionDemo {
     static ExecutorService executorService = new ThreadPoolExecutor(5,10,
            10, TimeUnit.SECONDS,new LinkedBlockingDeque<>());

     static class ConcurrencyException implements Runnable{

         @Override
         public void run() {
             Random r = new Random();
//             while(true){
                 int i1 = r.nextInt(100);
                 if(i1 == 20){
                     System.out.println(Thread.currentThread().getName()+222);
                     throw new RuntimeException();
                 }
                 System.out.println(Thread.currentThread().getName());
//             }
         }
     }
    public static void main(String[] args) {
        for (int i = 0; i <3 ; i++) {
//            executorService.submit(()->{
//                Random r = new Random();
//                while(true){
//                    int i1 = r.nextInt(100);
//                    if(i1 == 20){
//                        System.out.println(Thread.currentThread().getName());
//                        throw  new RuntimeException();
//                    }
//                }
//            });
            executorService.submit(new ConcurrencyException());

        }
    }
}
