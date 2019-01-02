package ja.java8;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.TreeSet;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.mapping;
import static java.util.stream.Collectors.maxBy;
import static java.util.stream.Collectors.partitioningBy;
import static java.util.stream.Collectors.summarizingInt;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;


/**
 * Created by yangst on 2018/6/21.
 */

public class CollectorDemo {

    public static void test() {

        StreamDemo.menu.stream().collect(toList());
    }

    public static void testSummarizingInt() {
        long i = StreamDemo.menu.stream().collect(summarizingInt(Dish::getCalories)).getSum();
        long t = StreamDemo.menu.stream().collect(summingInt(Dish::getCalories));
        System.out.println(i);
        System.out.println(t);
    }

    public static void testJoining() {
        StreamDemo.menu.stream().map(Dish::getName).collect(joining(","));
    }

    public static void testGroupingBy() {
        System.out.println(StreamDemo.menu.stream().collect(groupingBy(Dish::getType, maxBy(Comparator.comparingInt(Dish::getCalories)))));
//        System.out.println(StreamDemo.menu.stream().collect(groupingBy(Dish::getType,mapping()));
    }

    public static void testTypesCount() {
        System.out.println(StreamDemo.menu.stream().collect(groupingBy(Dish::getType, counting())));

    }

    public static void testCollectingAndThen() {
        System.out.println(StreamDemo.menu.stream().collect(groupingBy(Dish::getType, collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get))));
    }

    public static void testToMap() {
        System.out.println(StreamDemo.transactions.stream().collect(toMap(t -> t.getTrader().getName(), t -> t.getTrader().getCity(), (a, b) -> a + b)));
    }

    public static void testMapping() {

        System.out.println(StreamDemo.menu.stream().collect(groupingBy(Dish::getType, mapping(dish -> {
                    if (dish.getCalories() <= 400) return "DIET";
                    else if (dish.getCalories() <= 700) return "NORMAL";
                    else return "FAT";
                },
                toCollection(()->new TreeSet<>(new Comparator<String>() {

                    @Override
                    public int compare(String o1, String o2) {
                        if(o1.length()>o2.length()) return -1;
                        if(o1.length()<o2.length()) return 1;
                        return 0;
                    }
                }))))));

        //System.out.println(StreamDemo.menu.stream().collect(mapping(dish->{})));
    }


    public static void testPartitioningBy(){
        System.out.println(StreamDemo.menu.stream().collect(partitioningBy(Dish::isVegetarian,groupingBy(Dish::getType))));
    }



    public static void main(String[] args) {
        testSummarizingInt();
        LinkedList<Integer> list  = new LinkedList<Integer>();
list.spliterator();
        list.pop();
        list.pollLast();

        for(int i =0;i<100;i++){
            list.add(i);
        }
        System.out.println(list.stream().limit(5).collect(toList()));
//        List<Integer> integers = Collections.synchronizedList(new LinkedList<Integer>());

        //int d = 99;
        Map<Integer,Integer> map = new HashMap<>();
//        for(int i =0;i<list.size();i++){
//            Integer a = list.get(i);
//            Integer b = list.get(list.size()-1);
//            list.remove(b);
//            map.put(a,b);
//        }
//        int d = 99;
//        list.forEach(e->{
//            int b = list.get(list.size()-1);
//            list.remove(b);
//            map.put(e,b);
//        });
        System.out.println(map);
        testPartitioningBy();

        boolean swith = true;
        for (int i = 1,l = 0;l<10;i++){
            if(swith){
                if(i%2 == 0){
                    System.out.println(i);
                    l++;
                }
                if(i==10){
                    i=0;
                    swith=false;
                }
            }else{
                if(i%2 != 0){
                    System.out.println(i);
                    l++;
                }
            }
        }

    }


}
