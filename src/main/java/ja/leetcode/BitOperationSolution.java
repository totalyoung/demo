package ja.leetcode;


import java.util.Arrays;

public class BitOperationSolution {

    public static int[] findClosedNumbers(int num) {
        int[] one = numsOfOne(num);
        int i = one[0];
        int temp = num;
        int a = -1;
        int b = -1;
        while ((temp = temp - 1) > 0) {
            int[] ints = numsOfOne(temp);
            if (ints[0] > ints[1]) {
                break;
            }
            if (ints[0] == i) {
                a = temp;
                break;
            }
        }
        temp = num;
        while ((temp = temp + 1) > 0) {
            int[] ints = numsOfOne(temp);
            if (ints[0] > ints[1]) {
                break;
            }
            if (ints[0] == i) {
                b = temp;
                break;
            }
        }
        return new int[]{b, a};
    }

    public static int[] numsOfOne(int num) {
        int res = 0;
        int res2 = 0;
        while (num > 0) {
            if (num % 2 > 0) {
                res++;
            }
            num = num / 2;
            res2++;
        }
        return new int[]{res, res2};
    }

    public static int[] findClosedNumbers2(int num) {
        int temp = num;
        int a = 1;
        while((temp&1)!=1){
            a*=2;
            temp=temp>>1;
        }
        int res = num&a;
//        System.out.println(Integer.toBinaryString(num)+"："+Integer.toBinaryString(res));
        int less = (num-res)|(res>>1);
        int big = ((num-res)<<1)+1;
//        System.out.println(big+"："+less);
        return new int[]{big>0?big:-1,less>0?less:-1};
    }

//    public static int numsOfBit(int num){
//
//    }

    public static void main(String[] args) {
//        System.out.println(Arrays.toString(findClosedNumbers(31)));
//        System.out.println(Arrays.toString(findClosedNumbers(1)));
//        System.out.println(Arrays.toString(findClosedNumbers(2147483647)));
        System.out.println(findClosedNumbers2(2));
    }
}
