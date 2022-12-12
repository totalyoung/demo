package ja;


import sun.misc.Unsafe;
import sun.security.provider.MD5;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/26.
 */
public  class Test implements Cloneable,InterfaceA,InterfaceB{


    private int a;
    private int b;
    private List<Object> c;
    private static final Test unTest;

    static {
        unTest = new Test();
    }

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

    public void foo(String name){
        System.out.println(name);
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

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {

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

//
//        int[] i = new int[4];
//        i[3]=9;
//        System.out.println(Arrays.toString(i));
//        int run = 1 << (Integer.SIZE - 3);
//        int ca = (1 << (Integer.SIZE - 3)) - 1;
//        int ctl = run | 0;
//        int ctl1 = ctl +1;
//        System.out.println(ctl1 & ca);
//        Object o = Class.forName("").newInstance();
        //        System.out.println(0 << (Integer.SIZE - 3));
//        System.out.println(1 << (Integer.SIZE - 3));
//        System.out.println(2 << (Integer.SIZE - 3));
//        System.out.println((-1 << (Integer.SIZE - 3))|0);
//        System.out.println(2&-2);
//        System.out.println(Integer.toBinaryString(262144));
//        System.out.println(Integer.toHexString(262144));
//        System.out.println(Integer.toHexString(-65521));
//        System.out.println(Integer.toHexString(-262145));
//        System.out.println(Integer.toHexString(-32513));
//        System.out.println(java.lang.Long.toBinaryString(java.lang.Long.parseLong("FFFF000F",16)));
//        try {
//            testMath();
//        } catch (Exception e) {
//            System.out.println(e.printStackTrace());
//        }
//        List<String> list = new ArrayList<>();
//        list.add("1");
//        list.add("2");
//        list.add("3");
//        for (String integer : list) {
//            if("1".equals(integer))
//            list.remove(integer);
//        }
//        for (int i = 1; i < 10; i++) {
//            System.out.println(i&4);
//        }
//        Field unTest = Test.class.getDeclaredField("a");
//        Object o = unTest.get(null);
//        System.out.println(o);
//        System.out.println(null == new Object());
//        System.out.println(11&0x04);
//        System.out.println(11&0x08);
//        System.out.println(11&0x10);
//        Map<String,String> map = new HashMap<>();
            //最大线程数为2，任务队列数为1，理论上最多同时可提交3个任务
//            ThreadPoolExecutor tp = new ThreadPoolExecutor(0, 2, 100, TimeUnit.DAYS,
//                    new LinkedBlockingDeque<>(1));
//
//        try {
//            for (int i = 0; i < 3; i++) {
//                int finalI = i;
//
//                tp.execute(new Thread(() -> {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println("worker" + finalI + "..." + Thread.currentThread().getName());
//                }));
//
//                //当注释掉此行代码后，就会稳定出现RejectedExecutionException。
////            Thread.sleep(100);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        try{
//            try{testMath();
//
//            }catch (Exception e){
//                System.out.println("bb");
//            }
//        }catch (Exception e){
//            System.out.println("aa");
//            e.printStackTrace();
//        }
//        String[] str ={"baiHu","zhuQu","xuanWu","gouChen","tianFu"};
//        for (String s : str) {
//            System.out.println("if ("+s+"FightPower > feiShengGrowUpData.get"+s+"FeiShengPower()) {");
//            System.out.println( " feiShengGrowUpData.set"+s+"FeiShengPower("+s+"FightPower);");
//            System.out.println("}");
//        }

//        InterfaceA a = new Test();
//        InterfaceB b = new Test();
//        InterfaceA c = a;
//
//        System.out.println(a.hashCode());
//        System.out.println(b.hashCode());
//        System.out.println(c.hashCode());
//        System.out.println(a.equals(b));
//        String a = "a";
//        String b = "a";
//        System.out.println(a==b);
//        long l = System.currentTimeMillis();
//        StringBuffer bf = new StringBuffer();
//        for (int i = 0; i <100000 ; i++) {
//            bf.append(i);
//        }
//        System.out.println(System.currentTimeMillis()-l);
//        l = System.currentTimeMillis();
//        StringBuilder bb = new StringBuilder();
//        for (int i = 0; i <100000 ; i++) {
//            bb.append(i);
//        }
//        System.out.println(System.currentTimeMillis()-l);
//        System.out.println("a|b".split("|")[0]);
//        System.out.println(Integer.toHexString(536870913));
////        Unsafe unsafe = createUnsafe();
//        Unsafe unsafe1 = Unsafe.getUnsafe();
//        System.out.println();
//        String s = new String("中");
//        char[] chars = s.toCharArray();
//        System.out.println();
        System.out.println();

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


    @Override
    public void a() {

    }
}

 class Testa{
    private  int a ;


     public Testa(int a) {
         this.a = a;
     }
 }

 class Testb extends Testa{
    private String i;

     public Testb(String i,int a) {
         super(Integer.parseInt(i));
         this.i = i;
     }

 }
