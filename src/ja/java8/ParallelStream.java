package ja.java8;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * Created by yangst on 2018/7/23.
 */

public class ParallelStream {

    public static long meansureSumPerf(Function<Long,Long> adder,long n){
        long fastest =Long.MAX_VALUE;
        for(int i = 0;i<10;i++){
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start)/1_000;
            System.out.println("Result: "+ sum);
            if(duration <fastest) fastest = duration;
        }
        return fastest;
    }

    public static long sequentialSum(long n){
        return Stream.iterate(1L,i->i+1).limit(n).reduce(0L,Long::sum);

    }

    public static long iterativeSum(long n){
        long result = 0;
        for(long i =1L;i<=n;i++){
            result += i;
        }
        return result;
    }

    public static long parallelSum(long n){
        return Stream.iterate(1L,i->i+1).limit(n).parallel().reduce(0L,Long::sum);
    }

    public static long rangedSum(long n){
        return LongStream.rangeClosed(1,n).reduce(0L,Long::sum);
    }

    public static long parallelRangedSum(long n){
        return LongStream.rangeClosed(1,n).parallel().reduce(0L,Long::sum);
    }

    public static long sideEffectSum(long n){
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1,n).forEach(accumulator::add);
        return accumulator.total;
    }

    public static long sideEffectParallelSum(long n){
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1,n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }

    public static long forkJoinSum(long n){
        long[] numbers = LongStream.rangeClosed(1,n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }

    public static void main(String[] args) {
        System.out.println("iterativeSum sum done in: "+meansureSumPerf(ParallelStream::iterativeSum,1_000_000)+" mescs");
        System.out.println("Sequential sum done in: "+meansureSumPerf(ParallelStream::sequentialSum,1_000_000)+" mescs");
        System.out.println("parallelSum sum done in: "+meansureSumPerf(ParallelStream::parallelSum,1_000_000)+" mescs");
        System.out.println("rangedSum sum done in: "+meansureSumPerf(ParallelStream::rangedSum,1_000_000)+" mescs");
        System.out.println("parallelRangedSum sum done in: "+meansureSumPerf(ParallelStream::parallelRangedSum,1_000_000)+" mescs");
        System.out.println("sideEffectParallelSum sum done in: "+meansureSumPerf(ParallelStream::sideEffectParallelSum,1_000_000)+" mescs");
        System.out.println("forkJoinSum sum done in: "+meansureSumPerf(ParallelStream::forkJoinSum,1_000_000)+" mescs");

    }


}

