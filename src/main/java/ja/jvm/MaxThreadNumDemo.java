package ja.jvm;

public class MaxThreadNumDemo {

    public static void main(String[] args) {
        int num = 0;
        while (true){
            new Thread(new Runnable(){
                @Override
                public void run() {
                    while (true);
                }
            }).start();
            System.out.println(num++);
        }
    }
}
