package ja.jvm.ch2;

/**
 * Created by yangst on 2018/8/7.
 * VM args: -Xss128k
 */

public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak(){
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) {
        JavaVMStackSOF oom = new JavaVMStackSOF();
        try{
            oom.stackLeak();
        }catch (Throwable e){
            System.out.println("--------------"+oom.stackLength+"------------");
            throw  e;
        }
    }
}
