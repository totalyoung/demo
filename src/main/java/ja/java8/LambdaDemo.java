package ja.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;

/**
 * Created by yangst on 2018/5/25.
 */

public class LambdaDemo {

    private static Predicate<Integer> predicate = a -> a > 2;

    private static Consumer<Integer> consumer = (a) -> System.out.println(a);

    private static Consumer<Integer> consumer2 = System.out::println;

    private static Function<Integer,Long> function = (Integer a ) -> new Long(a);

    private static Function<Integer,Long> function2 = Long :: new;

    private static Function<Integer,Long[]> function3 = a -> { Long b =  new Long(a);
                                                                return new Long[]{b};};

    public static void testPredicate(Predicate<Integer> predicate) {

        System.out.println(predicate.test(5));
    }

    public static <T> void testConsumer(Consumer<T> consumer, List<T> list) {
        for (T t : list) {
            consumer.accept(t);
        }
    }

    public static <T,R> R testFunction(Function<T,R> function,T t){
        return function.apply(t);
    }

    public static void main(String[] args) {
        testPredicate(predicate);
        testConsumer(consumer2, Arrays.asList(1,2,3,4,5));
        Long l = testFunction(function2,new Integer(2));
        Long apply = function2.apply(100);
        System.out.println(l);
        StreamDemo st = new StreamDemo();

    }



}
