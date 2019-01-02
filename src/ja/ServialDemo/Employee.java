package ja.ServialDemo;

import java.util.Date;

/**
 * Created by totalyoung on 2018/10/19.
 */
public class Employee extends SerialCloneable {

    private String name;
    private double salary;
    private transient Date hireDay;
    private static int a =9;
//    private int i ;


    public static int getA() {
        return a;
    }

    public Employee(String name, double salary, Date hireDay) {
        this.name = name;
        this.salary = salary;
        this.hireDay = hireDay;
    }

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public Date getHireDay() {
        return hireDay;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", hireDay=" + hireDay +
                '}';
    }
}
