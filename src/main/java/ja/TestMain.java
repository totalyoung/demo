package ja;
import java.util.Scanner;
public class TestMain {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {// 注意，如果输入是多个测试用例，请通过while循环处理多个测试用例
            String[] str = in.nextLine().split(" ");
            int jin = Integer.parseInt(str[0]);
            String beijian = str[1];
            String jian=str[2];
            if(beijian.toCharArray()[0]==0||jian.toCharArray()[0]==0){
            System.out.println(-1);
            continue;
        }

        char[] bChars ;
        char[] jChars ;
        int isN=0;
        if(beijian.toCharArray().length>jian.toCharArray().length){
            bChars = beijian.toCharArray();
            jChars = jian.toCharArray();
        }else if (beijian.toCharArray().length<jian.toCharArray().length){
            jChars = beijian.toCharArray();
            bChars = jian.toCharArray();
            isN=1;
        }else{
            if(beijian.toCharArray()[0]>jian.toCharArray()[0]){
                bChars = beijian.toCharArray();
                jChars = jian.toCharArray();
            }else{
                jChars = beijian.toCharArray();
                bChars = jian.toCharArray();
                isN=1;
            }
        }

        char[] res = new char[bChars.length];
        boolean jianwei=false;
        for (int i = 0; i < jChars.length; i++) {
            if(!(isF(jin,bChars[i])&&isF(jin,jChars[i]))){
                System.out.println(-1);
                break;
            }
            int i1 = toNumber(bChars[i]);
            int i2 = toNumber(jChars[i]);
            if(jianwei){
                i1--;
            }
            if(i1>i2){
                res[i]=toChar(i1-i2);
            }else{
                i1+=jin;
                res[i]=toChar(i1-i2);
                jianwei=true;
            }
        }
        System.out.print(isN+" ");
        boolean isFirst=false;
        for (int i = res.length - 1; i >= 0; i--) {
            if (res[i] == 0 && !isFirst) continue;
            System.out.print(res[i]);
        }
    }
}

    public static boolean isF(int jin,char c){
        if(jin>=2&&jin<=10){
            return jin>(c-'0');
        }else{
            return jin>(c-'a'-10);
        }

    }

    public static char toChar(int c){
        if(c<=9&&c>=0){
            return (char)(48+c);
        }else{
            return (char)(87+c);
        }

    }

    public static int toNumber(char c){
        if(c<='9'&&c>='0'){
            return c-48;
        }else{
            return c-97;
        }
    }
}