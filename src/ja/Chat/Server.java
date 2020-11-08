package ja.Chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class Server {
    private  int port;
    private String ip;

    private ByteBuffer writeBuffer = ByteBuffer.allocate(1024);

    private ByteBuffer readBuffer = ByteBuffer.allocate(1024);

    public Server(int port, String ip) {
        this.port = port;
        this.ip = ip;
    }

    public void start() throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress(ip,port));
        ssc.configureBlocking(false);
        Selector se = Selector.open();
        ssc.register(se, SelectionKey.OP_ACCEPT);
        while (true) {
            se.select();
            Set<SelectionKey> selectionKeys = se.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey next = iterator.next();
                iterator.remove();
                if(next.isAcceptable()){
                    SocketChannel accept = ssc.accept();
                    accept.configureBlocking(false);
                    accept.register(se, SelectionKey.OP_READ);
                }else if (next.isReadable()) {
                    SocketChannel sc = (SocketChannel) next.channel();


                }else if(next.isWritable()){

                }
            }
        }
    }
}
