package ja.java8;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

/**
 * Created by yangst on 2018/7/9.
 */

public class CollectionDemo {

    private static Dish[] dishes = {new Dish("pork", false, 800, Dish.Type.MEAT),
            new Dish("beef", false, 700, Dish.Type.MEAT),
            new Dish("chicken", false, 400, Dish.Type.MEAT),
            new Dish("french fries", true, 530, Dish.Type.OTHER),
            new Dish("rice", true, 350, Dish.Type.OTHER),
            new Dish("season fruit", true, 120, Dish.Type.OTHER),
            new Dish("pizza", true, 550, Dish.Type.OTHER),
            new Dish("prawns", false, 300, Dish.Type.FISH),
            new Dish("salmon", false, 450, Dish.Type.FISH) };

    @Test
    public void test(){
        Arrays.stream(dishes).map(e->{e.setCalories(100);return e;}).forEach(System.out::println);
        Arrays.stream(dishes).forEach(System.out::println);
    }
    public static void main(String[] args) {
//        Map<Integer,Integer> map = new HashMap<>();
//        map.put(1,1);
//        int i = map.get(2);
//        System.out.println(map.get(2));

//        ArrayList<Dish> list = new ArrayList<>();
//        Arrays.stream(dishes).forEach(e->list.add(e));
//        list.sort(new DishComparator());
//        System.out.println(list);
//        Dish dish = list.get(0);
//        dish.setCalories(1000);
//        System.out.println(list);
//        Dish dish = new Dish("rice", true, 550, Dish.Type.OTHER);
//        list.remove(dish);
//        System.out.println(list);
//        list.add(dish);
//        Dish dish2 = new Dish("chicken", true, 44, Dish.Type.OTHER);
//        int i =  Collections.binarySearch(list,dish2,new DishComparator());
//        System.out.println(i);
        //
        //int i = StreamDemo.menu.indexOf(dish);
//        int d = 0;
//        for(int i=1;i<33;i++){
//            for(int j = i+1;j<33;j++){
//                System.out.print("{"+ (i<10 ? "0"+i: i) +" , "+(j<10 ? "0"+j: j)+"}");
//                d++;
//            }
//            System.out.println();
//        }
//        System.out.println(d);

        List<Dish> list = new LinkedList<>();
        for(Dish dish:dishes){
            list.add(dish);
        }
//
//        ListIterator<Dish> dishListIterator = list.listIterator(list.bodySize()/2);
//        ListIterator<Dish> dishListIterator2 = list.listIterator(list.bodySize()/2);
//        while(dishListIterator.hasNext()||dishListIterator2.hasPrevious()){
//            if(dishListIterator.hasNext()){
//                Dish next = dishListIterator.next();
//                if(next!=null){
//                    System.out.println(next);
//                }
//            }
//            if(dishListIterator2.hasPrevious()){
//                Dish previous = dishListIterator2.previous();
//                if(previous!=null){
//                    System.out.println(previous);
//                }
//            }
//
//
//        }

        Iterator iterator = list.iterator();
        for(Dish dish:list){
            if(dish.getName().equals("pork")){
                list.remove(dish);
            }
        }

        ConcurrentLinkedQueue<Dish> clist = new ConcurrentLinkedQueue<>();
        List<Dish> hasPoll = new CopyOnWriteArrayList<>();

        boolean isSuccess = false;
        boolean isInitiative = false;
        while(!isSuccess){

        }
        Dish poll = clist.poll();
        List<Dish> copyed = new CopyOnWriteArrayList<>();
        hasPoll.add(poll);
        copyed.addAll(clist);
        copyed.addAll(hasPoll);
        List<Dish> temp = copyed.stream().sorted(Comparator.comparing(Dish::getCalories)).collect(Collectors.toList());

        ListIterator<Dish> nextIterator = temp.listIterator(temp.size()/2);
        ListIterator<Dish> preIterator = temp.listIterator(temp.size()/2);
        int pollIndex = -1;
        while(nextIterator.hasNext()||preIterator.hasPrevious()) {
            if (nextIterator.hasNext()) {
                Dish next = nextIterator.next();
                if (next != null) {
                    if(next.getName().equals(poll.getName())){
                        pollIndex = nextIterator.nextIndex();
                        break;
                    }

                }
            }
            if (preIterator.hasPrevious()) {
                Dish previous = preIterator.previous();
                if (previous != null) {
                    if(previous.getName().equals(poll.getName())){
                        pollIndex = nextIterator.previousIndex();
                        break;
                    }
                }
            }
        }

        if(pollIndex == -1){
            isSuccess = true;
        }
        nextIterator = temp.listIterator(pollIndex);
        preIterator = temp.listIterator(pollIndex);
        Dish goal = new Dish("pork", false, 800, Dish.Type.MEAT);

        //加锁
        if(hasPoll.remove(poll)&&(clist.remove(goal) || hasPoll.remove(goal))){
            isSuccess = true;
            isInitiative = true;
        }

        if(isInitiative){
            //主动匹配成功
            //下发结果
        }


    }
}
