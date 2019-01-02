package ja.java8;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yangst on 2018/5/28.
 */

public class StreamDemo {

    public  static List<String> list;

    static List<Dish> menu = Arrays.asList(new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH) );

    static Trader raoul = new Trader("Raoul", "Cambridge");
    static Trader mario = new Trader("Mario","Milan");
    static Trader alan = new Trader("Alan","Cambridge");
    static Trader brian = new Trader("Brian","Cambridge");
    static List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300),
            new Transaction(raoul, 2012, 1000),
            new Transaction(raoul, 2011, 400),
            new Transaction(mario, 2012, 710),
            new Transaction(mario, 2012, 700),
            new Transaction(alan, 2012, 950)
    );


    public static void answer_1(){
        System.out.println(transactions.stream().filter(e->e.getYear() == 2011).sorted((a,b)->b.getTrader().getName().length()-a.getTrader().getName().length()).collect(Collectors.toList()));
//        System.out.println(transactions.stream().filter(e->e.getYear() == 2011).sorted().collect(Collectors.toList()));
    }

    public static void answer_2(){
        transactions.stream().map(Transaction::getTrader).map(Trader::getCity).distinct().collect(Collectors.toList());
    }

    public static void answer_3(){
        System.out.println(transactions.stream().map(Transaction::getTrader).filter(t->t.getCity().equals("Cambridge")).sorted(Comparator.comparing(Trader::getName).reversed()).distinct().collect(Collectors.toList()));
    }

    public static void answer_4(){
        transactions.stream().map(t->t.getTrader().getName()).distinct().sorted().forEach(System.out::print);
    }

    public static void answer_5(){
        transactions.stream().map(t->t.getTrader().getCity()).anyMatch(t->t.equals("Milan"));
    }

    public static void answer_6(){
        transactions.stream().filter(t->t.getTrader().getCity().equals("Cambridge")).map(Transaction::getValue).forEach(System.out::print);
    }

    public static void answer_7(){
        transactions.stream().map(Transaction::getValue).reduce(Integer::max);
    }

    protected static void answer_8(){
        transactions.stream().sorted(Comparator.comparing(Transaction::getValue)).findFirst().ifPresent(System.out::print);
    }

    public void an_8(){
        transactions.stream().sorted(Comparator.comparing(Transaction::getValue)).findFirst().ifPresent(System.out::print);
    }

    public static void testFilter(){
        List<Dish> list1 = menu.stream().filter((Dish d) -> d.isVegetarian()).collect(Collectors.toList());
        List<Dish> list2 = menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
        System.out.println(list1);
        System.out.println(list2);

    }

    public static void testDistinct(){
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream().filter((Integer i ) -> i % 2 == 0).distinct().forEach((Integer i ) -> System.out.println(i));
        numbers.stream().filter((Integer i ) -> i % 2 == 0).distinct().forEach(System.out::println);
    }

    public static void testLimit(){
        List<Dish> list = menu.stream().filter(d->d.getCalories()>300).limit(3).collect(Collectors.toList());
        System.out.println(list);
    }

    public static void testSkip(){
        List<Dish> list = menu.stream().filter(d->d.getCalories()>300).skip(2).collect(Collectors.toList());
        System.out.println(list);
    }

    public static void testMap(){
        List<String> list = menu.stream().map((Dish d) -> d.getName()).collect(Collectors.toList());
        List<String> list2 = menu.stream().map(Dish::getName).collect(Collectors.toList());
        System.out.println(list);
        System.out.println(list2);
    }

    public static void testMap2(){
        List<String> words = Arrays.asList("Java 8", "Lambdas", "In", "Action");
        List<Integer> list = words.stream().map(String::length).collect(Collectors.toList());
        List<Integer> list2 = menu.stream().map((Dish d) -> d.getName().length()).collect(Collectors.toList());
        System.out.println(list);
        System.out.println(list2);
    }

    public  static void testMap3(){
        List<Integer> list = menu.stream().map(Dish::getName).map(String::length).collect(Collectors.toList());
        System.out.println(list);
    }

    public static void testFlatMap(){
        List<String> words  = Arrays.asList("hello","word");
        List<String> list = words.stream().map(w->w.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
//        List<String> list2 = words.stream().map(String::).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
    }

    public static void testAnyMatch(){
        if(menu.stream().anyMatch(Dish::isVegetarian)){
            System.out.println("isVegetarian");
        }
    }

    public static void testFindAny(){
        menu.stream().filter(Dish::isVegetarian).findAny().ifPresent(d-> System.out.println(d.getName()));
    }

    public static void testReduce(){
        int sum = menu.stream().map(Dish::getCalories).reduce(0,(a, b)->a+b);
        System.out.println(sum);
    }

    public static void testMapToInt(){
        menu.stream().mapToInt(Dish::getCalories).sum();
    }

    //如果没有最大值的话，显式提供一个默认最大值
    public static void testBoxed(){
        menu.stream().mapToInt(Dish::getCalories).boxed();
    }

    public static void testOptionalInt(){
        menu.stream().mapToInt(Dish::getCalories).max().orElse(1);
    }
    public static void main(String[] args) {
        String s = "ss";
//        System.out.println(menu);
//        List<Dish> dishes = menu.subList(0, 2);
//        System.out.println(dishes);
        //dishes.add(dishes.size(), new Dish("yang", false, 700, Dish.Type.MEAT));
//        menu.add(new Dish("yang", false, 700, Dish.Type.MEAT));
//        System.out.println(dishes);
//        System.out.println(menu.stream().limit(8).collect(Collectors.toList()));
        List<Transaction> collect = transactions.stream().filter(e -> e.getYear() == 2088).collect(Collectors.toList());
        System.out.println(collect);
        answer_3();


    }

    class Dis{
        private final String name ;
        private final boolean vegetarian;
        private final int calories;

        public Dis(String name, boolean vegetarian, int calories) {
            this.name = name;
            this.vegetarian = vegetarian;
            this.calories = calories;

        }

        public synchronized String getName() {
            return name;
        }

        public boolean isVegetarian() {
            return vegetarian;
        }

        public int getCalories() {
            return calories;
        }

        @Override
        public String toString() {
            return getName();
        }

    }
}

class Dish{
    private  String name ;
    private  boolean vegetarian;
    private  int calories;
    private  Type type;

    public Dish(String name, boolean vegetarian, int calories, Type type) {
        this.name = name;
        this.vegetarian = vegetarian;
        this.calories = calories;
        this.type = type;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return  "{ name = "+ getName()+", calories = "+getCalories()+" }";
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Dish)obj).getName());
    }

    enum  Type{
        MEAT("meat",1),
        OTHER("other",2),
        FISH("fish",3);

        private String name;
        private int value;

        Type(String name, int value) {
            this.name = name;
            this.value = value;
        }
    }
}

class Transaction{
    private final Trader trader;
    private final int year;
    private final int value;
    public Transaction(Trader trader, int year, int value){
        this.trader = trader;
        this.year = year;
        this.value = value;
    }
    public Trader getTrader(){
        return this.trader;
    }
    public int getYear(){
        return this.year;
    }
    public int getValue(){
        return this.value;
    }
    public String toString() {
        return "{" + this.trader + ", " +
                "year: " + this.year + ", " +
                "value:" + this.value + "}";

    }
}

class Trader{
   private final String name;
   private final String city;
   public Trader(String n, String c){
       this.name = n;
       this.city = c;
   }
   public String getName(){
       return this.name;
   }
   public String getCity(){
       return this.city;
   }
    public String toString(){
        return "Trader:"+this.name + " in " + this.city;
    }
}

class DishComparator implements Comparator<Dish>{

    @Override
    public int compare(Dish o1, Dish o2) {
        if (o1.getName().equals(o2.getName())) return 0;
        if (o1.getCalories() >= o2.getCalories()) return 1;
        return -1;
    }
}





