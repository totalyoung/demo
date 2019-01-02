package ja.Concurrency.test;

/**
 * Created by totalyoung on 2018/9/22.
 */
public class Test {

    public static void main(String[] args) {
        Integer a = new Integer(1);
        Integer b = new Integer(2);
        swap(a,b);
        System.out.println(a);
        System.out.println(b);

        System.out.println(Integer.toHexString(-889275714));
    }

    public static void swap(Integer a ,Integer b){
        Integer temp;
        temp =a;
        a=b;
        b=temp;
        System.out.println(a);
        System.out.println(b);
    }

}
