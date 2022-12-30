package ja.Concurrency.joinDemo;

public class JoinDemo {
    static volatile  int i=0;
    static class JoinThread implements Runnable{
        @Override
        public void run() {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (;i<1000;i++);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        JoinThread joinThread = new JoinThread();
        Thread thread = new Thread(joinThread);
        thread.start();
        thread.join();
        System.out.println(i);
    }
}
