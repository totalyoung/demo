package ja;

/**
 * Created by totalyoung on 2018/10/24.
 */
public class Utility {

    public static void swapWithoutTemp(){
        int a = 2;
        int b = 3;
        a +=b;
        b=a-b;
        a -=b;
        System.out.println(a);
        System.out.println(b);
    }

    /**
     * hashMap初始化Entry的大小，返回最靠近cap的2的n次幂整数
     * 这个做的原因是在Entry寻址的时候，是用（n-1）& key.hashCode来定位的，能保证Entry的每个位置都能用到,除了Entry[0]
     * 例如：Entry的大小是16，16-1的二进制为1111，key.hashCode为111010，1111 & 111010=1010；
     *      Entry的大小是15，16-1的二进制为1110，key1.hashCode为111010，1110 & 111010=1010 ，
     *      key2.hashCode为111011，1110 & 111011=1010，这就造成了Entry地址的碰撞，还有一个更严重的问题是Entry地址的二进制尾数为1的地址都不能使用
     *
     */
    public static void tableSizeForHashMap(int cap){
        //先减1，是因为如果cap等于2的2次幂整数，就直接等于cap
        int n = cap-1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        n = (n < 0) ? 1 : (n >= Integer.MAX_VALUE) ? Integer.MAX_VALUE : n + 1;
        System.out.println(n);
    }


    public static void main(String[] args) {
        tableSizeForHashMap(9);
    }

}
