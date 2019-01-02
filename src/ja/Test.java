package ja;


import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * Created by Administrator on 2018/4/26.
 */
public class Test implements Cloneable{


    private int a;
    private int b;
    private List<Object> c;



    public Test() {
    }

    public Test(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public Object clone() {
        Object o = null;
        try {
            o = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return o;
    }

    public void setA(int a) {
        this.a = a;
    }

    public void setB(int b) {
        this.b = b;
    }

    public void setC(List<Object> c) {
        this.c = c;
    }
    @Override
    public String toString() {
        return "Test{" +
                "a=" + a +
                ", b=" + b +
                '}';
    }

    public void testExtends(){
//        System.out.println("test");
    }

    public void A(){
        B();
        System.out.println("A");
    }

    public void B(){
        C();
        System.out.println("B");
    }

    @org.junit.jupiter.api.Test
    public void C(){
        A();
        System.out.println("C");
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//       String str ="INSERT INTO `baggio_zone_data`.`t_achievement` (`userId`, `achievementType`, `taskId`, `eventType`, `completedNum`, `needNum`, `taskStatus`) VALUES ('100000176', '1', '1', '1', '0', '5', '1');\n";
//       for (int i =25 ;i<=60;i++){
//           System.out.print("INSERT INTO `baggio_zone_data`.`t_achievement` (`userId`, `achievementType`, `taskId`, `eventType`, `completedNum`, `needNum`, `taskStatus`) VALUES ('100000176', '1', '"+i+"', '1', '0', '5', '1');\n");
//       }

//        r.nextInt(1);
//        IntStream.range(1,100).forEach(e-> System.out.println(r.nextInt(9)));
//        TestClass testClass = new TestClass();
//        ja.jvm.ch7.TestClass testClass1 = new ja.jvm.ch7.TestClass();
//        System.out.println(System.getProperties());
//        List<Object> list = new ArrayList<>();
//        list.add("sdfa");
//        list.add(1);
//        for (Object o : list) {
//            System.out.println(o.getClass().getTypeName());
//        }
//
//        System.out.println(list instanceof List);
//
//        Test t = new Test();
//
//        Class<?> clazz = t.getClass();
//        System.out.println(Arrays.toString(clazz.getMethods()));
//        System.out.println(Arrays.toString(clazz.getDeclaredMethods()));
//        System.out.println(Arrays.toString(clazz.getDeclaredFields()));
//        System.out.println(Arrays.toString(clazz.getFields()));
//        try {
//            Field[] declaredFields = clazz.getDeclaredFields();
//            for (Field declaredField : declaredFields) {
//                System.out.println(declaredField.getName());
//                if(declaredField.getType().equals(List.class)){
//                    System.out.println(declaredField);
//                }
//            }
//            Method setA = clazz.getDeclaredMethod("setA",int.class);
//            setA.setAccessible(true);
//            setA.invoke(t,1666);
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        System.out.println(t);
//        System.out.println(2.0-1.2);
//        Random ra = new Random();
//        for (int i = 0; i < 100; i++) {
//            System.out.println(ra.nextInt(9));
//        }
//        t.A();
//        ThreadPoolExecutor executorService = new ThreadPoolExecutor(5,10,2, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10));
//        new ThreadPoolExecutor(5,10,2, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10))
//        for (int i = 0; i < 3; i++) {
//            RunnableDome runnableDome = new RunnableDome();
//            Future<?> submit = executorService.submit(runnableDome);
//            executorService.execute(runnableDome);
//        }
//        executorService.setRejectedExecutionHandler();
//        executorService.shutdown();
//        RunnableDome runnableDome = new RunnableDome();
//        runnableDome.run();
        Map<String,Object> map = new HashMap<>();
        String str = "aa";
        map.put(str,"aa");
//        map.put(null,"bb");
//        System.out.println();
//        map.put(null,"cc");
        System.out.println(map.get(null));
//
//        int[] i = new int[4];
//        i[3]=9;
//        System.out.println(Arrays.toString(i));
        int run = 1 << (Integer.SIZE - 3);
        int ca = (1 << (Integer.SIZE - 3)) - 1;
        int ctl = run | 0;
        int ctl1 = ctl +1;
        System.out.println(ctl1 & ca);
//        Object o = Class.forName("").newInstance();
        //        System.out.println(0 << (Integer.SIZE - 3));
//        System.out.println(1 << (Integer.SIZE - 3));
//        System.out.println(2 << (Integer.SIZE - 3));
//        System.out.println((-1 << (Integer.SIZE - 3))|0);
//        System.out.println(2&-2);
    }



//    @Override
//    public void run() {
//        ConcurrentHashMap<String, Object> concurrentHashMap = Main.getConcurrentHashMap();
//        for(int i = 0 ; i<10;i++){
//            concurrentHashMap.put(Thread.currentThread().getName(),i);
//        }
//        for (Map.Entry<String,Object> item:concurrentHashMap.entrySet()) {
//            System.out.println(item.getKey()+": "+item.getValue());
//        }
//    }

    public static void testMath() throws RuntimeException{
        throw new RuntimeException();
    }


}
