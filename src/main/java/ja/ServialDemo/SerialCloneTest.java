package ja.ServialDemo;

import java.util.Date;

/**
 * Created by totalyoung on 2018/10/19.
 */
public class SerialCloneTest {

    public static void main(String[] args) {
        Employee harry = new Employee("young",35000,new Date());
        Employee harry2 = (Employee) harry.clone();
        System.out.println(harry);
        System.out.println(harry2);
    }
}
