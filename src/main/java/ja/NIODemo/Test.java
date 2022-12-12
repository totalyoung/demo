package ja.NIODemo;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by admin on 2020/4/7.
 */
public class Test {
    public static void main(String[] args) throws IOException {
        scatterAndGatherNIO();
    }

    //读写同步
    public static void standardNIO()throws IOException{
        FileInputStream fin = new FileInputStream("C:\\Users\\admin\\Desktop\\web.xml");
        FileChannel channel = fin.getChannel();
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        FileOutputStream out = new FileOutputStream("C:\\Users\\admin\\Desktop\\web23.xml");
        FileChannel ch2 = out.getChannel();
        //将文件映射到内存，在底层io直接操作
        MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0, 1024);
        while(channel.read(allocate)>0){
            //把limit设为当前的position，并把position重置为0，
            allocate.flip();
            ch2.write(allocate);
            allocate.flip();
        }
    }

    public static void fastNIO()throws IOException{
        FileInputStream fin = new FileInputStream("C:\\Users\\admin\\Desktop\\web.xml");
        FileChannel channel = fin.getChannel();
        FileOutputStream out = new FileOutputStream("C:\\Users\\admin\\Desktop\\web23.xml");
        FileChannel ch2 = out.getChannel();
        //将文件映射到内存，在底层io直接操作
        MappedByteBuffer allocate = channel.map(FileChannel.MapMode.READ_ONLY, 0, 1024);
        while(channel.read(allocate)>0){
            //把limit设为当前的position，并把position重置为0，
            allocate.flip();
            ch2.write(allocate);
            allocate.flip();
        }
    }

    public static void scatterAndGatherNIO()throws IOException{
        FileInputStream fin = new FileInputStream("C:\\Users\\admin\\Desktop\\web.xml");
        FileChannel channel = fin.getChannel();
        FileOutputStream out = new FileOutputStream("C:\\Users\\admin\\Desktop\\web23.xml");
        FileChannel ch2 = out.getChannel();
        ByteBuffer allocate = ByteBuffer.allocate(1024);
//        ByteBuffer[] byteBuffers = new ByteBuffer(){allocate};
//        long read = channel.read(byteBuffers);
//        ch2.write(byteBuffers);
//        while(channel.read(allocate)>0){
//            //把limit设为当前的position，并把position重置为0，
//            allocate.flip();
//            ch2.write(allocate);
//            allocate.flip();
//        }
    }




}
