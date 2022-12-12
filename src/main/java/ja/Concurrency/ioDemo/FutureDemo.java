package ja.Concurrency.ioDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by totalyoung on 2018/9/11.
 */
public class FutureDemo {

    ExecutorService executorService = new ThreadPoolExecutor(10,5,
            10, TimeUnit.SECONDS,new LinkedBlockingDeque<>());

    void rederPage(CharSequence charSequence){
//        final List<ImageInfo>

    }


}
