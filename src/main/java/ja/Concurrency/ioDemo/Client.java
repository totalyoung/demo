package ja.Concurrency.ioDemo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by totalyoung on 2018/10/17.
 */
public class Client implements Runnable{

    public static void connet(){
        String ip = "127.0.0.1";
        try (Socket s = new Socket(ip,8189)){

//                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//                String line = br.readLine();
                InputStream inputStream = s.getInputStream();
                OutputStream outputStream = s.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream,true);
//                printWriter.println(Thread.currentThread().getName());

                printWriter.print("Esssasdfasdfasdfasdfasdfasdfasdasdfasdfasdfasdfasddfasdfasdfasdfasdfasdfasdfasdff");
            Thread.sleep(20000);
//                Scanner sc = new Scanner(inputStream);
//                while(sc.hasNextLine()){
//                    System.out.println(sc.nextLine());
//                }



        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        for (int i = 0; i <5 ; i++) {
//            new Thread(new Client()).start();
//        }
        connet();
    }

    @Override
    public void run() {
        connet();
    }
}
