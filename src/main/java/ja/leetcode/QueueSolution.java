package ja.leetcode;

import java.util.*;

public class QueueSolution {

    /**
     * 写一个 RecentCounter 类来计算特定时间范围内最近的请求。
     *
     * 请你实现 RecentCounter 类：
     *
     * RecentCounter() 初始化计数器，请求数为 0 。
     * int ping(int t) 在时间 t 添加一个新请求，其中 t 表示以毫秒为单位的某个时间，并返回过去 3000 毫秒内发生的所有请求数（包括新请求）。确切地说，返回在 [t-3000, t] 内发生的请求数。
     * 保证 每次对 ping 的调用都使用比之前更大的 t 值。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/number-of-recent-calls
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    class RecentCounter {

        private Queue<Integer> queue = new ArrayDeque();

        public RecentCounter() {
        }

        public int ping(int t) {
            queue.add(t);
            while (queue.peek() < t - 3000) {
                queue.poll();
            }
            return queue.size();
        }
    }

//    public int leastInterval(char[] tasks, int n) {
//        if(n==0) return tasks.length;
//
//
//    }

//    class

    public static List<List<String>> accountsMerge(List<List> accounts) {
        Map<String ,List<Set<String>>> map = new HashMap<>();
        for (List<String> account : accounts) {
            List<Set<String>> strings = map.get(account.get(0));
            if(strings!=null){
                boolean b = false;
                List<Set<String>> temp = new LinkedList<>();
                for (Set<String> stringSet : strings) {
                    for (String s : account.subList(1, account.size())) {
                        if(stringSet.contains(s)){
                            b=true;
                            break;
                        }
                    }
                    if(b){
                        stringSet.addAll(account.subList(1, account.size()));
                    }else{
                        temp.add(new TreeSet<>(account));
                    }
                }
                strings.addAll(temp);
            }else{
                Set<String> set = new TreeSet<>(account);
                List<Set<String>> list = new LinkedList<>();
                list.add(set);
                map.put(account.get(0),list);
            }
        }
        List<List<String>> result = new LinkedList<>();
        for (List<Set<String>> value : map.values()) {
            for (Set<String> strings : value) {
                result.add(new ArrayList<>(strings));
            }
        }
        return result;
    }

    public static void main(String[] args) {
//        List<List<String>> accounts = new LinkedList<>();
//        String str = "[[\"John\",\"johnsmith@mail.com\",\"john_newyork@mail.com\"],[\"John\",\"johnsmith@mail.com\",\"john00@mail.com\"],[\"Mary\",\"mary@mail.com\"],[\"John\",\"johnnybravo@mail.com\"]]";
//        List<List> lists = parseArray(str, List.class);
//        accountsMerge(lists);
//        System.out.println();
        List<String> list = new ArrayList<String>();
        list.add("2");
        list.add("1");
        for (String item : list) {
            if ("1".equals(item)) {
                list.remove(item);
            }
        }
        System.out.println(list);
    }

}
