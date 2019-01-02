package ja.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by yangst on 2018/7/18.
 */

public class PrimeNumberCollector implements Collector<Integer,Map<Boolean,List<Integer>>,Map<Boolean,List<Integer>>> {


    @Override
    public Supplier<Map<Boolean, List<Integer>>> supplier() {
        return ()->new HashMap<Boolean, List<Integer>>(){
            {put(true ,new ArrayList<>());
             put(false ,new ArrayList<>());}
        };
    }

    @Override
    public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
        return (acc,candidate)->{
            acc.get(isPrime(acc.get(true),candidate)).add(candidate);
        };
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
        return (map1,map2)->{
            map1.get(true).addAll(map2.get(true));
            map1.get(false).addAll(map2.get(false));
            return map1;
        };
    }

    @Override
    public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {

        return Collections.unmodifiableSet(EnumSet.of(Characteristics.IDENTITY_FINISH));
    }

    public static boolean isPrime(List<Integer> primes ,int candidate){
        int candidateRoot = (int)Math.sqrt((double)candidate);
        return takeWhile(primes,i->i<=candidateRoot).stream().noneMatch(p->candidate%p == 0);
    }

    public static Map<Boolean,List<Integer>> partitionPrimesWithCustomCollector(int n){
        return IntStream.rangeClosed(2,n).boxed().collect(new PrimeNumberCollector());
    }

    public static List<Integer> takeWhile(List<Integer> list, Predicate<Integer> p){
        int i = 0;
        for(Integer item : list){
            if(!p.test(item)){
                return list.subList(0,i);
            }
            i++;
        }
        return list;
    }

    public static boolean isPrime(int candidate){
        int candidateRoot = (int)Math.sqrt((double)candidate);
        return IntStream.rangeClosed(2,candidateRoot).noneMatch(i->candidate%i==0);
    }

    public static Map<Boolean,List<Integer>> partitionPrimes(int n){
        return IntStream.rangeClosed(2,n).boxed().collect(Collectors.partitioningBy(candidate->isPrime(candidate)));

    }

    public static void main(String[] args) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0 ;i<10;i++){
            long start = System.nanoTime();
            partitionPrimes(1_000_000);
            long duration = (System.nanoTime()-start)/1_000_000;
            if(duration<fastest) fastest = duration;
        }
        System.out.println(fastest);
        for (int i = 0 ;i<10;i++){
            long start = System.nanoTime();
            partitionPrimesWithCustomCollector(1_000_000);
            long duration = (System.nanoTime()-start)/1_000_000;
            if(duration<fastest) fastest = duration;
        }
        System.out.println(fastest);
    }
}
