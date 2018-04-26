public class Main {

    public void test(int a ){
        System.out.println(a);
    }

    public void test(int a ,Object... b){
        System.out.println(3+a+"asdfas");
    }

    public static void main(String[] args) {
        new Main().test(1);
        new Main().test(3);
    }
}
