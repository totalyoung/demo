package ja.Concurrency.callableDemo;

import java.util.concurrent.*;

/**
 * Created by admin on 2020/9/6.
 */
public class TestCallable {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> submit = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Done";
            }
        });
        String s = submit.get();
        System.out.println(s);
//        Calendar cal = Calendar.getInstance();
//        // 设置星期一为第一天
//        cal.setFirstDayOfWeek(Calendar.MONDAY);
//        System.out.println(cal.getFirstDayOfWeek());
    }
}
