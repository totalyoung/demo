package ja.Concurrency.CompletableFutureDemo;

import org.junit.After;
import org.junit.Test;

import java.util.concurrent.*;

public class CompletableFutureDemo {

    ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS,new LinkedBlockingQueue<>());
    @Test
    public void supplyAsync(){
        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync");
            return null;
        }, poolExecutor);
//        poolExecutor.submit(supplyAsync::join);
        CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync");
            return null;
        }, poolExecutor);
        try {
            String s = supplyAsync.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void thenCombine(){
        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync");
            return "supplyAsync";
        }, poolExecutor);
        CompletableFuture<String> supplyAsync2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync2");
            if(true) throw  new RuntimeException();
            return "supplyAsync2";
        }, poolExecutor);
        supplyAsync.thenCombine(supplyAsync2, (u,v)->{
            System.out.println(u+" "+v);
            return u+" "+v;
        }).exceptionally((e)->{
            System.out.println("exceptionally");
            return "exceptionally";
        });
    }

    @Test
    public void thenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> supplyAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println("supplyAsync");
            return "supplyAsync";
        }, poolExecutor).thenApply(str->str+"aaaa").thenAccept((str)->{
            str = str+"bbb";
            System.out.println(str);
        });
        System.out.println(supplyAsync.get());
    }

    @After
    public void testAfter(){
        try {
            poolExecutor.awaitTermination(200, TimeUnit.SECONDS);
            System.out.println("end----------------------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
//
//        CompletableFuture<Void> taskBlocker = new CompletableFuture<>();
//        System.out.println(taskBlocker.join());
        new CompletableFutureDemo().test2();

    }

    @Test
    public void test2(){
        int tries = 0;
        try {
            for (;; tries++)
                test();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.printf("tries = %d%n", tries);
        }
    }
     void test() throws Exception {
        int minThreads = 1;
        int maxThreads = 5;
        int queueCapacity = 10;

        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                minThreads, maxThreads,
                1, TimeUnit.HOURS,
                new LinkedBlockingQueue<Runnable>(queueCapacity));

        CompletableFuture.runAsync(() -> pool.setCorePoolSize(maxThreads));
        CompletableFuture<Void> taskBlocker = new CompletableFuture<>();

        try {
            for (int i = queueCapacity + maxThreads; i--> 0; ) {
                // following line sometimes throws a RejectedExecutionException
                pool.submit(taskBlocker::join);
            }
        } finally {
            taskBlocker.complete(null);
            pool.shutdown();
        }
    }
}
