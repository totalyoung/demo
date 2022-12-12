package ja.initDemo;

public class NotInitClass {
    private char a ;

    public static void main(String[] args) {
        float bb = 3.0f;
        NotInitClass notInitClass = new NotInitClass();
        System.out.println(notInitClass.a);
    }
}
