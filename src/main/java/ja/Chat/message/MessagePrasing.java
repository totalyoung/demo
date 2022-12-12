package ja.Chat.message;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class MessagePrasing {

    public static final int bodySize = 128;

    public static final int headerSize = 32;

//    public Message prasing(SocketChannel sc) throws IOException {
//        ByteBuffer header = ByteBuffer.allocate(headerSize);
//        ByteBuffer body = ByteBuffer.allocate(bodySize);
//        sc.read(header);
//        sc.read(body);
//        StringBuffer sb = new StringBuffer();
//        while(sc.read(allocate)== bodySize){
//            sb.append(new String(allocate.array(), "utf-8"));
//        }
//
//    }

}
