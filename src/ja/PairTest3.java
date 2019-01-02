package ja;

/**
 * Created by yangst on 2018/5/28.
 */

public class PairTest3 {

//    public static void printbuddies(Pair<? extends >);
//
//    class Pair<T>

}

class Pair<T>{

    private final T first;
    private final T second;

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public Pair() {
        first = null;
        second = null;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }


}

class Employee{
    private String name;
//    private
}