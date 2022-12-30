package netty.server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8080);
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter pr = new PrintWriter(outputStream);
        String str = "GET / HTTP/1.1\n" +
                "Host: localhost:8080\n" +
                "Content-Type: application/json\n" +
                "Content-Length: 12\n" +
                "\n" +
                "{\"aa\":\"123\"}";
        pr.print(str);
        pr.flush();
        socket.close();
    }
}
