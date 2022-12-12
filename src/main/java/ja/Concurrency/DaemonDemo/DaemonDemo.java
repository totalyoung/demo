package ja.Concurrency.DaemonDemo;

public class DaemonDemo {

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){

                }
            }
        });
        t.setDaemon(true);
        t.start();
    }
}
