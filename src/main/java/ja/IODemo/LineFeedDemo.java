package ja.IODemo;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by totalyoung on 2018/12/2.
 */
public class LineFeedDemo {

    @Test
    public void lineFeed() throws IOException {
        boolean upLineIsEng = true;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\totalyoung\\Desktop\\new 3.txt"))){
            String str ;
            StringBuilder sb = new StringBuilder();
            while ((str = bufferedReader.readLine())!= null ){
                if(str.length()>0) {
                    char start = str.charAt(0);
                    char end = str.charAt(str.length() - 1);
                    if (start == 9) {
                        sb.append(str).append("\n\r");
                        continue;
                    }
                    if ((start >= 65 && start <= 90) || (start >= 97 && start <= 122)) {
                        sb.append("\t").append(str);
                        continue;
                    }
                    sb.append("  ").append(str).append("\n\r");
                }
//                str = bufferedReader.readLine();
//                System.out.println(sb);
            }
            System.out.println(sb);
        }

    }
}
