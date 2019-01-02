package ja.ServialDemo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by totalyoung on 2018/10/21.
 */
public class Manager implements Serializable{

    private String name;
    private int age;
    private Employee[] employees;

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        name= in.readUTF();
        age = in.readInt();
        int employeesSize = in.readInt();
        if(employeesSize>0){
            employees = new Employee[employeesSize];
            for (int i = 0; i < employeesSize; i++) {
                employees[i] = (Employee)in.readObject();
            }
        }else{
            employees = null;
        }
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeUTF(name);
        out.writeInt(age);
        if(employees.length>0){
            out.writeInt(employees.length);
            for (Employee employee : employees) {
                out.writeObject(employee);
            }
        }
    }



}
