package ja.Concurrency.ThreadDemo;

/**
 * Created by admin on 2020/11/14.
 */
public class MyRunnable{

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("It is myRunnable");

            }
        });
        thread.start();
        Thread.sleep(3000);
        thread.interrupt();
    }

}
