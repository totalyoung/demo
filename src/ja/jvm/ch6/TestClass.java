package ja.jvm.ch6;

/**
 * Created by yangst on 2018/8/15.
 */

public class TestClass {
    private int m;
    private String[] strings;
    public int inc(){
        return m+1;
    }

    public TestClass() {
        System.out.println(this.getClass().getName());
    }

    public static void main(String[] args) {
//        String s ="";
//        for(int i =0;i<33106;i++){
//            System.out.println("private int _"+i+";");
//        }
        int a =1;
        int b =1;
        int c =a+b;
        System.out.println(0xff);
    }
}
