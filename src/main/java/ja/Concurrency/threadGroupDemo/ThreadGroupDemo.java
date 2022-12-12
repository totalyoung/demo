package ja.Concurrency.threadGroupDemo;

public class ThreadGroupDemo implements Runnable{
    @Override
    public void run() {

    }

    public static void main(String[] args) {
        ThreadGroup tg = new ThreadGroup("Group");
        Thread t1 = new Thread(tg,new ThreadGroupDemo(),"t1");
        Thread t2 = new Thread(tg,new ThreadGroupDemo(),"t2");
    }
}
