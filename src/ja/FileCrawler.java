package ja;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Administrator on 2018/5/14.
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
        try {
            crawl(root);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
    }

    private void crawl(File root) throws InterruptedException{
        File[] entries = root.listFiles(fileFilter);
        if(entries != null){
            for (File file : entries){
                if(file.isDirectory()){
                    crawl(file);
                }else if(!alreadyIndexed(file)){
                    fileQueue.put(file);
                }

            }
        }
    }

    private boolean alreadyIndexed(File file){
        return fileQueue.contains(file);
    }
}

