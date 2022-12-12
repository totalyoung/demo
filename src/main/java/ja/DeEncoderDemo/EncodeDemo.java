package ja.DeEncoderDemo;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class EncodeDemo {
    public static void main(String[] args) throws UnsupportedEncodingException {
        byte[] bytes = "中".getBytes("UTF-8");
        System.out.println(Arrays.toString(bytes));

        int res = Short.MAX_VALUE*2+2;
        for (int i = 0; i < res; i++) {
            System.out.print((char) i+" ");
        }
    }
}
