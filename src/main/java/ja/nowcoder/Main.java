package ja.nowcoder;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class Main {
    public static final Object lock = new Object();
    public static void main(String[] args) throws Exception {
////        printABCD(1000);
//        int a=2000;
//        int res=0;
//        for(int i=25;i*i<=a;i++){
//
//            int b =0;
//            while(i/10>0){
//                b++;
//                i=i/10;
//            }
//            if((i*i) % Math.pow(10,b) == i){
//                res++;
//            }
//        }
//        System.out.println(res);
////        String str = in.next();
//        char[] chars = str.toCharArray();
//        LinkedList list = new LinkedList();
//        Stack stack = new Stack();
//        for(int i=0;i<chars.length;i++){
//            if(chars[i]!=(char)stack.peek()){
////                stack.push(char[i])
//            }
//        }
////        bigDecimal.
//        double d = 1;
//        for (int i = 0; i <10 ; i++) {
//            d =d/2;
//            System.out.println(d);
//        }
//        String a = "97.5.217.231";
//        String[] strs = a.split(".");
//        for(String str : strs){
//            int i = Integer.parseInt(str);
//            if(i>255||i<0){
//                System.out.println("NO");
//                return;
//            }
//        }
//        System.out.println("YES");
    }

    public static void printABCD(int a ) throws  Exception{

        final CountDownLatch cdl = new CountDownLatch(4);
        final char[] chars = new char[4*a+1];
        chars[0]='D';
        for(int i =0;i<4;i++){
            final char c = (char)('A'+i);
            new Thread(new Runnable(){
                public void run(){

                    test(c,a,chars,cdl);
                }
            }).start();
        }
        cdl.await();
        char[] res = Arrays.copyOfRange(chars,0,chars.length);
        System.out.println(new String(res));
    }

    static void test(char c, int round, char[] chars, CountDownLatch cdl) {
        for (int i = 0; i < round; i++) {
            int index = i * 4;
            char pre = 'D';
            if (c == 'B') {
                index += 1;
                pre = 'A';
            } else if (c == 'C') {
                index += 2;
                pre = 'B';
            } else if (c == 'D') {
                index += 3;
                pre = 'C';
            }
            long l = System.currentTimeMillis();
//            char c1 = chars[index];
            while (chars[index] != pre) {
                char c1 = chars[index];
                if(System.currentTimeMillis()-l>1000){
//                    char c2 = chars[index];
                    System.out.println("超时：index "+ index + " pre "+pre  +" c "+ c +" c1 "+c1);
                    System.out.println(c+" res "+chars[index] );
                    System.out.println(c+" res2 "+chars[index] );
                    return;
                }
            }
            synchronized (lock){
                chars[++index] = c;
                System.out.println(index+"_"+chars[index]+"_"+c);
            }
        }
        cdl.countDown();
    }
}
