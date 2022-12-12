package ja.Concurrency.ioDemo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by totalyoung on 2018/10/17.
 */
public class Server {

    public static void main(String[] args) {
        try (ServerSocket ss = new ServerSocket(8189)) {
            Socket accept = ss.accept();
            InputStream inputStream =accept.getInputStream();
            OutputStream outputStream = accept.getOutputStream();
            Scanner sc = new Scanner(inputStream);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream),5);
            PrintWriter out = new PrintWriter(outputStream,true);
            out.println("enter Exit to end");
            boolean done = false;
            String line =reader.readLine();
            while(line !=null){
                System.out.println(line);
                line =reader.readLine();
            }
            System.out.println("hah ");
            while(sc.hasNextLine()){
                String nextLine = sc.nextLine();
                System.out.println(nextLine);
                if(nextLine.trim().equals("Exit")){
                    done =true;
                    out.println(" Exit");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
