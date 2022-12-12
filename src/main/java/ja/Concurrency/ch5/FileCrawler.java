package ja.Concurrency.ch5;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

/**
 * Created by totalyoung on 2018/7/21.
 */
public class FileCrawler implements Runnable{
    private final BlockingQueue<File> fileQueue;
    private final FileFilter fileFilter;
    private final File root;

    public FileCrawler(BlockingQueue<File> fileQueue, FileFilter fileFilter, File root) {
        this.fileQueue = fileQueue;
        this.fileFilter = fileFilter;
        this.root = root;
    }

    @Override
    public void run() {

    }

    public void crawl(File root) throws InterruptedException {
        File[] entries = root.listFiles(fileFilter);
        if(entries != null){
            for(File entry : entries){
                if(entry.isDirectory()){
                    crawl(entry);
//                }else if(){
//                    fileQueue.put(entry);
                }
            }
        }
    }
    public static void main(String[] args) {

    }
}
