package ja.Concurrency.ch6;

import java.util.*;
import java.util.concurrent.*;

/**
 * Created by totalyoung on 2018/9/17.
 */
public class FutureRederer {

    ThreadPoolExecutor executorService = new ThreadPoolExecutor(10,5,
            10, TimeUnit.SECONDS,new LinkedBlockingDeque<>());

    void rederPage(CharSequence source) throws ExecutionException {
        final List<ImageInfo> imageInfos = scanForImageInfo(source);
        Callable<List<ImageData>> task = new Callable<List<ImageData>>() {
            @Override
            public List<ImageData> call() throws Exception {
                List<ImageData> result = new ArrayList<>();
                for (ImageInfo imageInfo : imageInfos) {
                    result.add(imageInfo.downloadImage());
                }
                return result;
            }
        };

        Future<List<ImageData>> future = executorService.submit(task);
        rederText(source);
        try{
            List<ImageData> imageData = future.get();
            for (ImageData data : imageData) {
                renderImage(data);
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            future.cancel(true);
        }catch (ExecutionException e){
            throw launderException(e.getCause());
        }
    }

    void rederPage2(CharSequence source) throws ExecutionException {
        final List<ImageInfo> imageInfos = scanForImageInfo(source);
        CompletionService<ImageData> completionService = new ExecutorCompletionService<ImageData>(executorService);
        for (final ImageInfo imageInfo : imageInfos) {
            completionService.submit(new Callable<ImageData>() {
                @Override
                public ImageData call() throws Exception {
                    return imageInfo.downloadImage();
                }
            });
        }
        rederText(source);
        try{
            for (int i = 0; i < imageInfos.size(); i++) {
                Future<ImageData> f = completionService.take();
                ImageData data = f.get();
                renderImage(data);
            }
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }catch (ExecutionException e){
            throw launderException(e.getCause());
        }
    }

    List<ImageInfo> scanForImageInfo(CharSequence source){
        return new LinkedList<>();
    }

    void rederText(CharSequence source){

    }

    void renderImage(ImageData data){

    }

    ExecutionException launderException(Throwable e){
        return new ExecutionException(e);
    }

    public List<TravelQuote> getRankedTravelQuotes(TravelInfo travelInfo, Set<TravelCompany> companies,
                                                   Comparator<TravelQuote> ranking,long time ,TimeUnit unit) throws InterruptedException {
        List<QuoteTask> tasks = new ArrayList<>();
        for (TravelCompany company : companies) {
            tasks.add(new QuoteTask(company,travelInfo));
        }

        List<Future<TravelQuote>> futures = executorService.invokeAll(tasks,time,unit);

        List<TravelQuote> quotes = new ArrayList<>(tasks.size());
        Iterator<QuoteTask> taskIter = tasks.iterator();
        for (Future<TravelQuote> future : futures) {
            QuoteTask task = taskIter.next();
            try {
                quotes.add(future.get());
            } catch (ExecutionException e) {
                quotes.add(task.getFailureQuote(e.getCause()));
            } catch(CancellationException e ){
                quotes.add(task.getTimeoutQuote(e));
            }
        }

        Collections.sort(quotes,ranking);
        return quotes;
    }

    private class QuoteTask implements Callable<TravelQuote>{
        private final TravelCompany company;
        private final TravelInfo travelInfo;

        public QuoteTask(TravelCompany company, TravelInfo travelInfo) {
            this.company = company;
            this.travelInfo = travelInfo;
        }

        @Override
        public TravelQuote call() throws Exception {
            return company.solicitQuote(travelInfo);
        }

        public TravelQuote getFailureQuote(Throwable throwable){
            return new TravelQuote();
        }

        public TravelQuote getTimeoutQuote(Throwable throwable){
            return new TravelQuote();
        }

    }


}
