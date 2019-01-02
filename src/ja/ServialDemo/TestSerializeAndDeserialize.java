package ja.ServialDemo;

import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by totalyoung on 2018/10/20.
 */
public class TestSerializeAndDeserialize {

    public static void serializeObject(Object o){
//        Boolean isAppend = true;
//        File file = new File("E:\\Object.txt");
//        if(file.exists() && file.isFile()){
//            if(file.length()>0){
//
//            }
//        }
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("E:\\Object.txt"));
        ) {
            out.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Object Deserialize(){
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("E:\\Object.txt"))
        ) {
            Object o = in.readObject();
            return o;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        Employee harry = new Employee("young",35000,new Date());
        Employee harry1 = new Employee("young",3522,new Date());
        Employee harry2 = harry;
        System.out.println(harry2 == harry);
//        serializeObject(harry);
//        serializeObject(harry1);
//        Deserialize();
//        Employee deserialize = (Employee) Deserialize();
//        System.out.println(deserialize);
//        System.out.println(deserialize.getA());
//        Map<String,Employee> map = new LinkedHashMap<>(12,0.75f,true);
//        map.put("aa",harry);
//        map.put("bb",harry1);
//        map.put("cc",harry1);
//        map.put("aa",harry);
//        System.out.println(map.hashCode());

    }
}
