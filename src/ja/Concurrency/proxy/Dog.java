package ja.Concurrency.proxy;

/**
 * Created by totalyoung on 2018/10/18.
 */
public class Dog implements Animal {

    @Override
    public void eat() {
        System.out.println("eat");
    }
}
