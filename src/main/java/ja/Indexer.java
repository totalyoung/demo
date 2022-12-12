package ja; /**
 * Created by yangst on 2018/5/14.
 */

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * @Description:
 * @Author yangst
 * @Date 2018/5/14
 **/
public class Indexer implements Runnable{
    private final BlockingQueue<File> queue;

    public Indexer(BlockingQueue<File> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while(true){
                indexFile(queue.take());
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    private void indexFile(File file){
        System.out.println(file.getName());
    }

}
