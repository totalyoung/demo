package ja.Concurrency.ch5;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by totalyoung on 2018/8/12.
 */
public class Preloader {
    private final FutureTask<ProductInfo> futureTask = new FutureTask<>(()->
        loadProductInfo()


//            new Callable<ProductInfo>() {
//        @Override
//        public ProductInfo call() throws Exception {
//            return loadProductInof();
//        }
//    }
    );

    public Thread getThread() {
        return thread;
    }

    private final Thread thread = new Thread(futureTask);

    public static RuntimeException launderThrowable(Throwable throwable) {
        if (throwable instanceof RuntimeException)
            return (RuntimeException) throwable;
        if (throwable instanceof Error)
            throw (Error) throwable;
        throw new IllegalStateException("not unchecked " + throwable);
    }

    public ProductInfo get() throws DataLoadException, InterruptedException {
        try{
            futureTask.run();
            return futureTask.get();
        }catch (ExecutionException e ){
            Throwable cause = e.getCause();
            if(cause instanceof DataLoadException)
                throw (DataLoadException)cause;
            else
                throw launderThrowable(cause);
        }
    }
    public ProductInfo loadProductInfo() throws DataLoadException, IOException, InterruptedException {
        FileInputStream in = new FileInputStream("E:\\ideaSet\\Concurrency\\src\\ch5\\Preloader.java");
//        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        byte[] bys = new byte[in.available()];
        in.read(bys);
//        while(br.readLine()!=null){
//
//        }
        String str = new String(bys,"UTF-8");
        Thread.sleep(10000);
        return new ProductInfo(str);
    }

//    class ProductInfo {
//        private String str;
//
//        public ProductInfo(String str) {
//            this.str = str;
//        }
//
//        public ProductInfo() {
//        }
//
//        public String getStr() {
//            return str;
//        }
//    }

    public FutureTask<ProductInfo> getFutureTask() {
        return futureTask;
    }

    public static void main(String[] args) throws InterruptedException {
        Preloader preloader = new Preloader();
        preloader.getFutureTask().run();
        for(int i= 0;i<10;i++){
            System.out.println(i);
        }
        ProductInfo productInfo = preloader.get();
        System.out.println(productInfo.getStr());

    }
}

