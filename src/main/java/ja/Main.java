package ja;

import java.io.File;
import java.io.FileFilter;
import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
    private static final Integer[] a = {1,2,3};

    private static ConcurrentHashMap<String ,Object> concurrentHashMap = new ConcurrentHashMap();

    public void test(int a ){
        System.out.println(a);
    }

    public void test(int a ,Object... b){
        System.out.println(3+a+"asdfas");
    }

    public static ConcurrentHashMap<String, Object> getConcurrentHashMap() {
        return concurrentHashMap;
    }

    public static void startIndexing(File[] roots){
        BlockingQueue<File> queue = new LinkedBlockingQueue<>(10);
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return true;
            }
        };
        for(File root : roots){
            new Thread(new FileCrawler(queue,fileFilter,root)).start();
        }

        for(int i = 0; i<5;i++){
            new Thread(new Indexer(queue)).start();
        }
    }

    public static void testMemoizerl() throws InterruptedException {
        ExpensiveFunction expensiveFunction = new ExpensiveFunction();
        Memoizerl<String,BigInteger> memoizerl = new Memoizerl<>(expensiveFunction);
        Memoizerl<String,BigInteger> memoizerl2 = new Memoizerl<>(memoizerl);
        memoizerl.compute("123");
        memoizerl2.compute("123");
    }

    public static void main(String[] args) throws InterruptedException {
        testMemoizerl();

//        Test t = new Test(1,2);
//        Thread th = new Thread(t);
//        Thread th2 = new Thread(t);
//        th.start();
//        th2.start();

//        File file = new File("E:\\svn\\x1Client");
//        startIndexing(file.listFiles());

//        Test t2 = (Test) t.clone();
//        String str = "aa";

//        List<Test> tl = new ArrayList<>();
//        tl.add(t);
//        tl.add(t2);
//        List<Test> tl2 = new ArrayList<>();
//        Test t3 = t;
//        tl2.add(t3);
//        t3.setA(3);
//        Map<String ,Test> map = new HashMap<>();
//        map.put("a",t);
//        map.get("a").setA(2);
//        System.out.println(map);
//
//        List<Integer> integers = Arrays.asList(a);
//        List<Integer> b = new ArrayList<>();
//        for (int i = 0; i<2;i++) {
//            b.add(integers.get(i));
//        }
//        for (int i = 0; i<2;i++) {
//           b.set(i,8);
//        }

//        t2.setA(4);
//        System.out.println(t);
//        System.out.println(1141%1000/10 + "-" + 1141%1000%10 );

    }
}
