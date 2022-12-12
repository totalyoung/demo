package ja.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class StackSolution {

    /**
     * 剑指 Offer 30. 包含min函数的栈
     * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
     *
     *  
     *
     * 示例:
     *
     * MinStack minStack = new MinStack();
     * minStack.push(-2);
     * minStack.push(0);
     * minStack.push(-3);
     * minStack.min();   --> 返回 -3.
     * minStack.pop();
     * minStack.top();      --> 返回 0.
     * minStack.min();   --> 返回 -2.
     *  
     *
     * 提示：
     *
     * 各函数的调用总次数不超过 20000 次
     *
     */
    static class MinStack {
        private Node head;

        public MinStack() {

        }

        public void push(int x) {

            if (head == null)
                head = new Node(x, x, null);
            else
                head = new Node(x, Math.min(head.min, x), head);
        }

        public void pop() {

            head = head.next;
        }

        public int top() {

            return head.val;
        }

        public int min() {

            return head.min;
        }
    }

    /**
     * 请你设计一个支持下述操作的栈。
     *
     * 实现自定义栈类 CustomStack ：
     *
     * CustomStack(int maxSize)：用 maxSize 初始化对象，maxSize 是栈中最多能容纳的元素数量，栈在增长到 maxSize 之后则不支持 push 操作。
     * void push(int x)：如果栈还未增长到 maxSize ，就将 x 添加到栈顶。
     * int pop()：弹出栈顶元素，并返回栈顶的值，或栈为空时返回 -1 。
     * void inc(int k, int val)：栈底的 k 个元素的值都增加 val 。如果栈中元素总数小于 k ，则栈中的所有元素都增加 val 。
     *
     */
    static class CustomStack {
        private int[] arr;
        private int index;

        public CustomStack(int maxSize) {
            arr = new int[maxSize];
            index = -1;
        }

        public void push(int x) {
            if (index == arr.length - 1) return;
            arr[++index] = x;
        }

        public int pop() {
            if (index == -1)
                return -1;

            return arr[index--];
        }

        public void increment(int k, int val) {
            for (int i = 0; i < Math.min(k, index + 1); i++)
                arr[i] += val;
        }
    }

    static class Node {

        int val;
        int min;
        Node next;

        public Node(int val, int min, Node next) {

            this.val = val;
            this.min = min;
            this.next = next;
        }
    }

    /**
     * 字符串解码
     * @param s
     * @return
     */
    public static String decodeString(String s) {
        Stack<Integer> nums = new Stack();
        Stack<String> op = new Stack();
        int num=0;
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if(c=='['){
                nums.push(num);
                num=0;
                op.push(Character.toString(c));
            } else
            if(c>='0'&&c<='9'){
                num = num*10 -'0'+c;
            } else
            if(c==']'){
                StringBuilder st = new StringBuilder();
                String pop;
                while(!"[".equals(pop=op.pop())){
                    st.append(pop);
                }
                Integer sum = nums.pop();
                while(sum-- > 0){
                    op.push(st.toString());
                }
            } else
            if(c>='a'&&c<='z'){
                op.push(Character.toString(c));
            }
        }
        while(op.size()>0){
            sb.append(op.pop());
        }
        return sb.reverse().toString();
    }

    /**
     * 有效的括号
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack();
        for (char c : s.toCharArray()) {
            if(c=='(' ||c=='{'||c=='['){
                stack.push(c);
            }else{
                Character pop;
                if(stack.size()==0 || (pop=stack.pop())==null){
                    return false;
                }
                if(c==')'&& pop!='('){
                    return false;
                }
                if(c=='}'&& pop!='{'){
                    return false;
                }
                if(c==']'&& pop!='['){
                    return false;
                }
            }
        }
        return stack.size()==0;
    }

    /**
     * 括号的分数
     * @param s
     * @return
     */
    public static int scoreOfParentheses(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> op = new Stack<>();
        boolean ca = true;
        int re=0;
        for (char ch : chars) {
            if(ch=='('){
                op.push(ch);
                ca=true;
            }else{
                if(ca){
                    ca=false;
                    re+=Math.pow(2,op.size()-1);
                }
                op.pop();
            }
        }
        return re;
    }
    public static void main(String[] args) {
//        CustomStack minStack = new CustomStack(3);
//        minStack.push(1);
//        minStack.push(2);
//        System.out.println(minStack.pop());
//        minStack.push(2);
//        minStack.push(3);
//        minStack.push(4);
//        System.out.println();
//        System.out.println(Math.pow(2,-1));
//        System.out.println(decodeString("10[a2[c]]"));;

        int[] a = new int[]{1,3,1};
        List<Integer> list = new ArrayList<>();
        for (int i : a) {
            if(i>=a.length/2){
                list.add(i);
            }
        }
        for (Integer integer : list) {
            if(integer==list.size()){
                System.out.println(integer);
            }
        }
    }
}
