package ja.Concurrency.ioDemo;

import java.util.Arrays;

/**
 * Created by totalyoung on 2018/10/13.
 */
public class HexToDeci {

    /**
     * 暂时不支持大数据操作，可用BigInteger代替
     */
    public void hexToDeci(){
        String str = "4d2";
        char[] chars = {'1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        String[] split = str.split("");
        System.out.println(Arrays.toString(split));
        int sum = 0;
        for(int i = 0;i<str.length();i++){
            char c = str.charAt(i);
            int mi = 0;
            for (int i1 = 0; i1 < chars.length; i1++) {
                if(c==chars[i1]){
                    mi = i1+1;
                    break;
                }
            }

            sum +=(Math.pow(16,(str.length()-1-i)))*mi;
        }
        System.out.println(sum);
    }
}
