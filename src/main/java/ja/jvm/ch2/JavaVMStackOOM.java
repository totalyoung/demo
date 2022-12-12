package ja.jvm.ch2;

/**
 * Created by yangst on 2018/8/8.
 * VM args:-Xss2M
 * 不要在Windows下运行此代码，会造成死机
 */

public class JavaVMStackOOM {

    private void dontStop(){
        while (true){

        }
    }

    public void stackLeakByThread(){
        while(true){
            Thread thread = new Thread(()->dontStop());
            thread.start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
