package ja.Chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class LocalClient implements Runnable{
    private int id;

    private String ip;

    private int port;

    private ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

    private ByteBuffer readBuffer = ByteBuffer.allocate(1024);

    private SocketChannel sc;

    public LocalClient(int id, String ip, int port) {
        this.id = id;
        this.ip = ip;
        this.port = port;
    }

    public void connet(){
        try {
            sc = SocketChannel.open();
            sc.bind(new InetSocketAddress(ip,port));
            writeBuffer.put("1".getBytes());
            sc.write(writeBuffer);

        } catch (IOException e) {
            e.printStackTrace();
            try {
                sc.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public void close(){
        try {
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void read() throws IOException {
        readBuffer.clear();
        int read = sc.read(readBuffer);
        if(read>=0){
            System.out.println(new String(readBuffer.array()));
        }

    }

    public void write() throws IOException {
        writeBuffer.clear();
        sc.write(writeBuffer);
    }

    public void chat(String connet)throws IOException{
        writeBuffer.clear();
        writeBuffer.put(connet.getBytes());
        sc.write(writeBuffer);
    }

    @Override
    public void run() {
        while(true){
            try {
                read();
            } catch (IOException e) {
               close();
            }
        }
    }
}
