import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yangst on 2018/10/10.
 */

public class ThreadDeadLock {
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    static class callable implements Callable<String>{

        @Override
        public String call() throws Exception {
            return null;
        }
    }
}
