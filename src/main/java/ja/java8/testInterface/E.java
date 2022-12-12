package ja.java8.testInterface;

/**
 * Created by yangst on 2018/7/25.
 */
public interface E extends A {
    default void hello(){
        System.out.println("interface e");
    }

}
