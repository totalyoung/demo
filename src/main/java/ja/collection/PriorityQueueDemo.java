package ja.collection;

import java.util.*;
import java.util.concurrent.*;

public class PriorityQueueDemo {

    public void test(){
        PriorityQueue priorityQueue = new PriorityQueue(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1<=o2?-1:1;
            }
        });

        priorityQueue.offer(2);
        priorityQueue.offer(4);
        priorityQueue.offer(3);
        priorityQueue.offer(8);
        priorityQueue.offer(9);
        System.out.println(priorityQueue);
    }

    public static void main(String[] args) throws InterruptedException {
        ConcurrentSkipListMap<Object, Object> map = new ConcurrentSkipListMap<>();
//        for (int i = 0; i < 15; i++) {
//
//        }
        map.put(1,null);
        System.out.println(map);



    }
}
