package ja;
import java.util.*;

public class TestMain2 {
    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
////        while (in.hasNextLine()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
////            String str = in.nextLine();
////            String[] split = str.split(",");
////            int[] ints = new int[split.length];
////            for (int i = 0; i < split.length; i++) {
////                ints[i]=Integer.parseInt(split[i]);
////            }
//            int[] ints = new int[]{1,-5,-6,4,3,6,-2};
//
//            int[] resInt = new int[ints.length];
//            int res = 0;
//            int index = 0;
//            for (int i = 0; i < ints.length; i++) {
//                if(i<=3){
//                    index=0;
//                }else{
//                    index++;
//                }
//                if(res+ints[i]>=res||res+ints[i]>=resInt[index]){
//                    res+=ints[i];
//                    resInt[i]=res;
//                }else{
//                    res =resInt[i];
//                }
//            }
//            System.out.println(resInt[ints.length-1]);
//        }
//        test();
//        System.out.println(Arrays.toString("a哈".getBytes()));
//        System.out.println(Arrays.toString("a哈".toCharArray()));
        System.out.println(Integer.toBinaryString(Float.floatToIntBits(12.1F)));
        System.out.println(Integer.parseInt(Integer.toBinaryString(Float.floatToIntBits(12.1F)),2));
    }

    public static void  test(){
        String str ="adc12341cd";
        char[] chars = str.toLowerCase().toCharArray();
        boolean b = false;
        int res=0;
        int aIndex,bIndex;
        LinkedList list = new LinkedList<>();
        for (int i = 1; i < chars.length; i++) {
            if(chars[i]>='0'&&chars[i]>='9'&&chars[i-1]>='a'&&chars[i-1]>='z'){
                res++;
            }else{
                if(res>0){
                    list.add(res);
                    res=0;
                }
            }
        }
        Collections.sort(list);
        System.out.println(list.getLast());
    }

}
