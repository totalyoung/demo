package ja.leetcode;

import java.util.*;

/**
 * @author yst
 * @create 2019/2/11
 */
public class Solution_1 {

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

     你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

     示例:

     给定 nums = [2, 7, 11, 15], target = 9

     因为 nums[0] + nums[1] = 2 + 7 = 9
     所以返回 [0, 1]

     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i <nums.length ; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if(nums[i]+nums[j]==target){
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return null;
    }

    public static void testTwoSum(){
        int[] nums = {2, 7, 11, 15,5,9,10,23};
        int target = 21;
        int[] result = twoSum(nums,target);
        System.out.println(Arrays.toString(result));
    }


    /**
     * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

     示例 1:

     输入: 123
     输出: 321
     示例 2:

     输入: -123
     输出: -321
     示例 3:

     输入: 120
     输出: 21
     注意:

     假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−231,  231 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
     * @param x
     * @return
     *
     * 解题思路：对10取余，然后除于10缩小值大小，
     */
    public static int reverse(int x) {
        int a = x,result = 0;
        while (a !=0) {
            long temp = (long)result* 10;
            //乘法，判断溢出
            if((int)temp != temp){
                return 0;
            }
            //对10取余,获取个位数
            int d = a % 10;
            int temp2 = (int)temp;
            result = temp2 + d;
            //加法，判断溢出
            if(((temp2 ^ result) & (d ^ result)) < 0){
                return 0;
            }
            //除于10,缩小值大小
            a = a /10;
        }
        return result;
    }

    public static void  testReverse(){
        int x = -2147411212;
        System.out.println(reverse(x));
    }

    /**
     * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

     示例 1:

     输入: 121
     输出: true
     示例 2:

     输入: -121
     输出: false
     解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
     示例 3:

     输入: 10
     输出: false
     解释: 从右向左读, 为 01 。因此它不是一个回文数。
     * @param x
     * @return
     *
     * 解题思路：先取反，再判断只是否一样
     */
    public static boolean isPalindrome(int x) {
        if(x<0){
            return false;
        }
        int a = x,result = 0;
        while (a !=0) {
            long temp = (long)result* 10;
            if((int)temp != temp){
                return false;
            }
            int d = a % 10;
            int temp2 = (int)temp;
            result = temp2 + d;
            if(((temp2 ^ result) & (d ^ result)) < 0){
                return false;
            }
            a = a /10;
        }
        return x==result;
    }

    public static void testIsPalindrome(){
        int x = 121121;
        System.out.println(isPalindrome(x));
    }

    /**
     * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。

     字符          数值
     I             1
     V             5
     X             10
     L             50
     C             100
     D             500
     M             1000
     例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。

     通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：

     I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
     X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
     C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
     给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。

     示例 1:

     输入: "III"
     输出: 3
     示例 2:

     输入: "IV"
     输出: 4
     示例 3:

     输入: "IX"
     输出: 9
     示例 4:

     输入: "LVIII"
     输出: 58
     解释: L = 50, V= 5, III = 3.
     示例 5:

     输入: "MCMXCIV"
     输出: 1994
     解释: M = 1000, CM = 900, XC = 90, IV = 4.
     * @param s
     * @return
     *
     * 解题思路：先判断最后两个字符是否特殊，如有特殊再减去倒数第二位的值
     */
    public static int romanToInt(String s) {
        char[] ch = {'I','V','X','L','C','D','M'};
        int[] in = {1,5,10,50,100,500,1000};
        char[] chars = s.toCharArray();
        int result=0;
        String str="";
        int lastIndex= 0;
        for (int i = 0; i < chars.length; i++) {
            int nowIndex=0;
            char nowChar=' ';
            for (int i1 = 0; i1 < ch.length; i1++) {
                if(chars[i] == ch[i1]){
                    nowIndex = i1;
                    nowChar = ch[i1];
                }
            }
            str+=nowChar;
            if ("IV".equals(str)||"IX".equals(str)||"XL".equals(str)||"XC".equals(str)||"CD".equals(str)||"CM".equals(str)) {
                result = result+ in[lastIndex]*-2;
            }
            result += in[nowIndex];
            if(str.length()>1){
                str="";
                str+=nowChar;
            }
            lastIndex = nowIndex;
        }
        return result;
    }

    /**
     * 输出：500
     58
     9
     1994
     */
    public static void testRomanToInt(){
        System.out.println(romanToInt("D"));
        System.out.println(romanToInt("LVIII"));
        System.out.println(romanToInt("IX"));
        System.out.println(romanToInt("MCMXCIV"));
    }


    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。

     如果不存在公共前缀，返回空字符串 ""。

     示例 1:

     输入: ["flower","flow","flight"]
     输出: "fl"
     示例 2:

     输入: ["dog","racecar","car"]
     输出: ""
     解释: 输入不存在公共前缀。
     说明:

     所有输入只包含小写字母 a-z 。

     * @param strs
     *   本人解法
     * @return
     */
//    public static String longestCommonPrefix(String[] strs) {
//        if(strs.length<1) return "";
//        for (int i = 1; i < strs.length; i++) {
//            for(int j=i;j>0;j--){
//                if(strs[j].length()<strs[j-1].length()){
//                    String temp = strs[j];
//                    strs[j] = strs[j-1];
//                    strs[j-1] = temp;
//                }
//            }
//        }
//        String shortStr = strs[0];
//        for (int i = shortStr.length(); i >0 ; i--) {
//            boolean flag = true;
//            for (String str : strs) {
//                flag = shortStr.substring(0,i).equals(str.substring(0,i)) && flag;
//            }
//            if(flag){
//                    return shortStr.substring(0,i);
//                }
//        }
//        return "";
//    }

    /**
     * 编写一个函数来查找字符串数组中的最长公共前缀。
     * @param strs
     * @return
     *
     * 水平扫描法
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        for (int i = 0; i < strs[0].length() ; i++){
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j ++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
        }
        return strs[0];
    }


    /**
     * 输出："fl","c",""
     */
    public static void testLongestCommonPrefix(){
        System.out.println(longestCommonPrefix(new String[]{"flower","flow","flight"}));
        System.out.println(longestCommonPrefix(new String[]{"c","cc","ccc"}));
        System.out.println(longestCommonPrefix(new String[]{"aca","cba"}));
    }

    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

     有效字符串需满足：

     左括号必须用相同类型的右括号闭合。
     左括号必须以正确的顺序闭合。
     注意空字符串可被认为是有效字符串。

     示例 1:

     输入: "()"
     输出: true
     示例 2:

     输入: "()[]{}"
     输出: true
     示例 3:

     输入: "(]"
     输出: false
     示例 4:

     输入: "([)]"
     输出: false
     示例 5:

     输入: "{[]}"
     输出: true
     * @param s
     * @return
     *
     * 解题思路：加入栈，如栈中元素数量大于1，则开始判断最后两个是否是括号组合，如是则弹出
     */
    public static boolean isValid(String s) {
        if (s== null || s.length()<1) return true;

        char[] chars = s.toCharArray();
        Stack stack = new Stack();
        for (char c : chars) {
            if(stack.size<1){
                stack.add(c);
                continue;
            }
            char last = stack.pop();
            if (!((last == '(' &&  c == ')')
                    || (last == '[' && c == ']')
                    || (last == '{' && c == '}'))) {
                stack.add(last);
                stack.add(c);
            }
        }
        if(stack.getSize()>0){
            return false;
        }
        return true;
    }

    static class Stack{

        int size;
        Node last;

        public void add(char data){
            if(size ==0){
                last = new Node(data,null);
                size++;
                return;
            }

            Node temp = last;
            last = new Node(data,null);
            last.previous = temp;
            size++;
        }

        public void prin(){
            Node temp  = last;
            do{
                System.out.println(temp.data);
                temp = temp.previous;
            }while(temp !=null);
        }

        public char pop(){
            Node temp = last;
            if(last.previous!=null){
                last =last.previous;
            }
            size--;
            return temp.data;
        }

        public char getLast(){
            return last.data;
        }
        public char getPrevious(){
            if(last.previous !=null){
                return last.previous.data;
            }
            return ' ';
        }

        public int getSize(){
            return size;
        }

        class Node{
            char data;
            Node previous;
            public Node(char data, Node previous) {
                this.data = data;
                this.previous = previous;
            }
        }
    }

    /**
     * 输出：true false true
     */
    public static void testIsValid(){
        System.out.println(isValid("()[]{}"));
        System.out.println(isValid("(]"));
        System.out.println(isValid("{[]}"));
    }

    /**
     * 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。

     示例：

     输入：1->2->4, 1->3->4
     输出：1->1->2->3->4->4
     * @param l1
     * @param l2
     * @return
     *解题思路：分别比较两个链表的值，小者则添加，如有一个链表先比较完，则直接添加另一个链表
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        l1= createListNode(new int[]{2});
        l2= createListNode(new int[]{1});
        if(l1 ==null){
            return l2;
        }
        if(l2 ==null){
            return l1;
        }
        ListNode curr =new ListNode(0);
        ListNode result =curr;
        do{
            if(l1.val <=l2.val){
                curr.next = l1;
                l1 = l1.next;
                //如有一个为空，则直接添加另一个链表
                if(l2 !=null && l1 ==null){
                    curr = curr.next;
                    curr.next = l2;
                    break;
                }
            }else{
                curr.next = l2;
                l2 = l2.next;
                if(l2 ==null && l1 !=null){
                    curr = curr.next;
                    curr.next = l1;
                    break;
                }
            }
            curr = curr.next;
        }while(l1!=null || l2 !=null);
        return result;
    }

    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

//    public int getIndex(int low,int high,ListNodeHandler ln1,int val2){
//        int middle = (low+high)/2;
//        int val1 = ln1.getListNode(middle).val;
//        if(val1>=val2){
//            high = middle;
//        }else{
//            low = middle;
//        }
//        while(middle != low){
//            getIndex(low,high,ln1,val2);
//        }
//        return middle;
//    }

    public static ListNode createListNode(int[] ints){
        ListNode result =null;
        ListNode curr =null;
        for (int i = 0; i < ints.length; i++) {
            if(result ==null && curr ==null){
                curr = result = new ListNode(ints[i]);
                continue;
            }
            curr.next = new ListNode(ints[i]);
            curr = curr.next;
        }
        return result;
    }


    public static void testMergeTwoLists() {
        ListNode l1= createListNode(new int[]{2});
        ListNode l2= createListNode(new int[]{1});
        mergeTwoLists(l1, l2);
    }
//    static class ListNodeHandler{
//        private int size;
//        private ListNode listNode;
//
//        public ListNodeHandler(ListNode listNode) {
//            if(listNode == null){
//                return;
//            }
//            this.listNode = listNode;
//            do{
//                size++;
//
//            } while((this.listNode = this.listNode.next)!=null);
//        }
//
//        public ListNode getListNode(int index){
//            int a = 0;
//            while(true){
//                if(a==index){
//                    return listNode;
//                }
//                listNode = listNode.next;
//                a++;
//            }
//        }
//
//        public int getSize(){
//            return size;
//        }
//    }

    /**
     * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。

     不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。

     示例 1:

     给定数组 nums = [1,1,2],

     函数应该返回新的长度 2, 并且原数组 nums 的前两个元素被修改为 1, 2。

     你不需要考虑数组中超出新长度后面的元素。
     示例 2:

     给定 nums = [0,0,1,1,1,2,2,3,3,4],

     函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。

     你不需要考虑数组中超出新长度后面的元素。
     * @param nums
     * @return
     */
    public static int removeDuplicates(int[] nums) {
        if (nums==null || nums.length==0 ) return 0;

        int i =1;
        for (int t=1,length=0; i < nums.length && t<nums.length; t++){
            if(nums[i-1] == nums[i]){
                for (int j = i; j < nums.length-length; j++) {
                    nums[j-1] = nums[j];
                }
                length++;
            }else{
                i++;
            }
        }
        return i;
//        int i =1;
//        int a=0;
//        boolean isDo = false;
//        int length  =0;
//        for (int t=1; i < nums.length && t<nums.length;){
//            if(nums[i-1] == nums[i]){
//                a++;
//            }else{
//                isDo = true;
//            }
//            if(a>0 && isDo){
//                for (int j = i; j < nums.length-length; j++) {
//                    nums[j-a] = nums[j];
//                }
//                length+=a;
//                i= i-a;
//                a=0;
//                isDo = false;
//                continue;
//            }
//            t++;
//            i++;
//            isDo = false;
//        }
//        if(a>0){
//            return nums.length-length-a;
//        }
//        return nums.length-length;

    }

    /**
     * 输出：1 3 5 2 4
     */
    public static void testRemoveDuplicates(){
        System.out.println(removeDuplicates(new int[]{0,0,0}));
        System.out.println(removeDuplicates(new int[]{1,2,2,2,2,3,3,3,}));
        System.out.println(removeDuplicates(new int[]{0,0,1,1,1,2,2,3,3,4}));
        System.out.println(removeDuplicates(new int[]{1,1,2,2}));
        System.out.println(removeDuplicates(new int[]{-3,-1,-1,0,0,0,0,0,2}));
    }


    /**
     * 给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。

     不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。

     元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。

     示例 1:

     给定 nums = [3,2,2,3], val = 3,

     函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。

     你不需要考虑数组中超出新长度后面的元素。
     示例 2:

     给定 nums = [0,1,2,2,3,0,4,2], val = 2,

     函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。

     注意这五个元素可为任意顺序。

     你不需要考虑数组中超出新长度后面的元素。
     * @param nums
     * @param val
     * @return
     */
    public static int removeElement(int[] nums, int val) {
        if(nums.length ==0) return 0;
        int b =nums.length;
        for (int i=0; i < b; ){
            if(nums[i]==val){
                for(int j=i+1;j<b;j++){
                    nums[j-1]=nums[j];
                }
                b--;
            }else{
                i++;
            }
        }
        return b;

    }

    /**
     *  输出 5 5
     */
    public static void testRemoveElement(){
        System.out.println(removeElement(new int[]{1,1,1,1,1},0));
        System.out.println(removeElement(new int[]{0,1,2,2,3,0,4,2},2));
    }

    /**
     * 实现 strStr() 函数。

     给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。

     示例 1:

     输入: haystack = "hello", needle = "ll"
     输出: 2
     示例 2:

     输入: haystack = "aaaaa", needle = "bba"
     输出: -1
     说明:

     当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。

     对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
     * @param haystack
     * @param needle
     * @return
     */
    public static int strStr(String haystack, String needle) {
        if(needle.length()==0) return  0;
        if(needle.length()>haystack.length()) return -1;

        char[] str = haystack.toCharArray();
        char[] ne = needle.toCharArray();
        for (int i = 0; i+ne.length <=str.length ; i++) {
            int j = i;
            for (; j < i+ne.length; j++) {
                if(str[j]!=ne[j-i]){
                    break;
                }
            }
            if(ne.length== j-i) return i;
        }

        return -1;
    }

    /**
     *输出 2 1 -1
     */
    public static void testStrStr(){
        System.out.println(strStr("yangst","ng"));
        System.out.println(strStr("yangst","an"));
        System.out.println(strStr("yangst","yng"));
    }


    /**
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

     你可以假设数组中无重复元素。

     示例 1:

     输入: [1,3,5,6], 5
     输出: 2
     示例 2:

     输入: [1,3,5,6], 2
     输出: 1
     示例 3:

     输入: [1,3,5,6], 7
     输出: 4
     示例 4:

     输入: [1,3,5,6], 0
     输出: 0
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {
        if (target<=nums[0]) return 0;
        if(target==nums[nums.length-1]) return nums.length-1;
        if(target>nums[nums.length-1]) return nums.length;
        int low = 0;
        int high = nums.length-1;
        int middle = (low+high)/2;
        while(middle!=low){
            if(target>nums[middle]){
                low = middle;
            }else if(target<nums[middle]){
                high = middle;
            }else{
                return middle;
            }
            middle = (high+low)/2;
        }
        return middle+1;
    }


    /**
     * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

     示例:

     输入: [-2,1,-3,4,-1,2,1,-5,4],
     输出: 6
     解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
     进阶:

     如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
     * @param nums
     * @return
     *
     *
     * -----未实现---------------------------
     */
    public static int maxSubArray(int[] nums) {
//        int maxIndex=0;
//        int d=0;
//        int[] maxInt = new int[nums.length];
//        for (int i = 0; i < nums.length; i++) {
//            if(nums[i]>nums[maxIndex]){
//                maxIndex = i;
//                d=0;
//            }
//            if(nums[i]==nums[maxIndex]){
//                maxInt[d]=i;
//                d++;
//            }
//        }
//        int r2=nums[maxInt[0]];
//        int temp3;
//        int temp;
//        for (int i = 0; i < d; i++) {
//            int a=maxInt[i],b=maxInt[i];
//            int sum1=nums[maxInt[i]],sum2=nums[maxInt[i]];
//            int temp1 =sum1,temp2=sum2;
//            while(a>=0 || b<nums.length){
//                if((--a)>=0){
//                    sum1+=nums[a];
//                }
//                if((++b)<nums.length){
//                    sum2+=nums[b];
//                }
//                temp1=temp1>=sum1?temp1:sum1;
//                temp2=temp2>=sum2?temp2:sum2;
//            }
//            temp3 = temp1+temp2-nums[maxInt[i]];
//            temp =temp1>=temp2?temp3>=temp1?temp3:temp1:temp3>=temp2?temp3:temp2;
//            r2=temp>=r2?temp:r2;
//        }
//        return r2;
        return nums[0];
    }

    /**
     * 给定一个仅包含大小写字母和空格 ' ' 的字符串，返回其最后一个单词的长度。

     如果不存在最后一个单词，请返回 0 。

     说明：一个单词是指由字母组成，但不包含任何空格的字符串。

     示例:

     输入: "Hello World"
     输出: 5
     * @param s
     * @return
     */
    public static int lengthOfLastWord(String s) {
        String trim = s.trim();
        char[] chars = trim.toCharArray();
        int start=0;
        for (int i = 0; i < chars.length; i++) {
            if(chars[i]== ' '){
                start=i+1;
            }
        }
        return trim.substring(start).length();
    }


    /**
     * 给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。

     最高位数字存放在数组的首位， 数组中每个元素只存储一个数字。

     你可以假设除了整数 0 之外，这个整数不会以零开头。

     示例 1:

     输入: [1,2,3]
     输出: [1,2,4]
     解释: 输入数组表示数字 123。
     示例 2:

     输入: [4,3,2,1]
     输出: [4,3,2,2]
     解释: 输入数组表示数字 4321。
     * @param digits
     * @return
     */
    public static int[] plusOne(int[] digits) {
        int bit = 1;
        for (int i = digits.length-1; i >=0 ; i--) {
            if(bit==1){
                if(++digits[i]>=10){
                    digits[i]=0;
                    bit=1;
                }else{
                    bit=0;
                }
            }
        }
        if(bit==1){
            int[] result = new int[digits.length+1];
            result[0]=bit;
            for (int i = 0; i < digits.length; i++) {
                result[i+1] = digits[i];
            }
            return result;
        }else{
            return digits;
        }
    }

    /**
     * 给定两个二进制字符串，返回他们的和（用二进制表示）。

     输入为非空字符串且只包含数字 1 和 0。

     示例 1:

     输入: a = "11", b = "1"
     输出: "100"
     示例 2:

     输入: a = "1010", b = "1011"
     输出: "10101"
     * @param a
     * @param b
     * @return
     */
    public static String addBinary(String a, String b) {
        char[] as = a.toCharArray();
        char[] bs = b.toCharArray();
        char[] temp = as.length>=bs.length?as:bs;
        int bit=0,c,d;
        for (int i = as.length-1,j=bs.length-1; i >=0||j>=0; i--,j--) {
            c=0;
            d=0;
            if(i>=0){
                c=as[i]=='0'?0:1;
            }
            if(j>=0){
                d=bs[j]=='0'?0:1;
            }
            temp[i>=j?i:j]=(c+d+bit)%2==1?'1':'0';
            if(c+d+bit>=2){
                bit =1;
            }else{
                bit =0;
            }
        }
        StringBuilder sb = new StringBuilder();
        if(bit==1){
            return sb.append(bit).append(temp).toString();
        }else{
            return sb.append(temp).toString();
        }
    }


    /**
     * 实现 int sqrt(int x) 函数。

     计算并返回 x 的平方根，其中 x 是非负整数。

     由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。

     示例 1:

     输入: 4
     输出: 2
     示例 2:

     输入: 8
     输出: 2
     说明: 8 的平方根是 2.82842...,
     由于返回类型是整数，小数部分将被舍去。
     * @param x
     * @return
     */
    public static int mySqrt(int x) {

        return x;
    }


    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。

     每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？

     注意：给定 n 是一个正整数。

     示例 1：

     输入： 2
     输出： 2
     解释： 有两种方法可以爬到楼顶。
     1.  1 阶 + 1 阶
     2.  2 阶
     示例 2：

     输入： 3
     输出： 3
     解释： 有三种方法可以爬到楼顶。
     1.  1 阶 + 1 阶 + 1 阶
     2.  1 阶 + 2 阶
     3.  2 阶 + 1 阶
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        return n;
    }


    /**
     * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。

     示例 1:

     输入: 1->1->2
     输出: 1->2
     示例 2:

     输入: 1->1->2->3->3
     输出: 1->2->3
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode temp = head;
        while(head!=null && head.next!=null){
            if(head.val == head.next.val){
                head.next = head.next.next;
            }else{
                head = head.next;
            }
        }
        return temp;

    }

    /**
     * 给定两个有序整数数组 nums1 和 nums2，将 nums2 合并到 nums1 中，使得 num1 成为一个有序数组。

     说明:

     初始化 nums1 和 nums2 的元素数量分别为 m 和 n。
     你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
     示例:

     输入:
     nums1 = [1,2,3,0,0,0], m = 3
     nums2 = [2,5,6],       n = 3

     输出: [1,2,2,3,5,6]
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        for(int i =0,j=0;i<n;i++){
            while(nums1[j]<=nums2[i]){
                if(j==m){
                    break;
                }
                j++;
            }
            for(int k = m;k>j;k--){
                nums1[k]=nums1[k-1];
            }
            nums1[j++]=nums2[i];
            m++;
        }
        System.out.println(Arrays.toString(nums1));
    }

    public static void testMerge(){
        merge(new int[]{2,2,3,0,0,0},3,new int[]{1,5,6},3);
        merge(new int[]{},0,new int[]{},0);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 给定两个二叉树，编写一个函数来检验它们是否相同。

     如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。

     示例 1:

     输入:     1         1
     / \       / \
     2   3     2   3

     [1,2,3],   [1,2,3]

     输出: true
     示例 2:

     输入:     1         1
     /           \
     2             2

     [1,2],     [1,null,2]

     输出: false
     示例 3:

     输入:       1         1
     / \       / \
     2   1     1   2

     [1,2,1],   [1,1,2]

     输出: false
     * @param p
     * @param q
     * @return
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if(p==null && q==null){
            return true;

        }else if(p!=null && q!=null){
            if(p.val == q.val){
                return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
            }
        }
        return false;
    }

    public static void testIsSameTree(){
        TreeNode t = new TreeNode(1);
        t.left=new TreeNode(2);
        t.right = new TreeNode(3);
        TreeNode t2 = new TreeNode(1);
        t2.left=new TreeNode(2);
//        t2.left=null;
        t2.right = new TreeNode(3);
        System.out.println(isSameTree(t,t2));
    }

    /**
     * 给定一个二叉树，检查它是否是镜像对称的。

     例如，二叉树 [1,2,2,3,4,4,3] 是对称的。

     1
     / \
     2   2
     / \ / \
     3  4 4  3
     但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:

     1
     / \
     2   2
     \   \
     3    3
     说明:

     如果你可以运用递归和迭代两种方法解决这个问题，会很加分。
     * @param root
     * @return
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root==null) return true;
        return isSymmetric(root.left,root.right);

    }

    public static boolean isSymmetric(TreeNode p,TreeNode q){
        if(p==null && q==null){
            return true;

        }else if(p!=null && q!=null){
            if(p.val == q.val){
                return isSymmetric(p.left,q.right) && isSymmetric(p.right,q.left);
            }
        }
        return false;
    }

    public static void testIsSymmetric(){
        TreeNode t = new TreeNode(1);
        t.left=new TreeNode(2);
        t.right = new TreeNode(2);
        t.left.left = new TreeNode(3);
        t.left.right = new TreeNode(4);
        t.right.left = new TreeNode(4);
        t.right.right = new TreeNode(3);
        System.out.println(isSymmetric(t));
    }


    /**
     * 给定一个二叉树，找出其最大深度。

     二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

     说明: 叶子节点是指没有子节点的节点。

     示例：
     给定二叉树 [3,9,20,null,null,15,7]，

     3
     / \
     9  20
     /  \
     15   7
     返回它的最大深度 3 。
     * @param root
     * @return
     */
    public static int maxDepth(TreeNode root) {
        if(root != null){
            return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
        }
        return 0;
    }

//    public static int maxDepth(TreeNode root,int count) {
//        if(root !=null ){
//            ++count;
//            int count1=count,count2=count;
//            if( root.left!=null){
//                count1=maxDepth(root.left,count);
//            }
//            if(root.right !=null){
//                count2=maxDepth(root.right,count);
//            }
//            return count1>=count2?count1:count2;
//        }else{
//            return count;
//        }
//
//    }

    public static void testMaxDepth(){
        TreeNode t = new TreeNode(1);
        t.left=new TreeNode(2);
        t.right = new TreeNode(2);
        t.right.left = new TreeNode(4);
        t.right.right = new TreeNode(3);
        System.out.println(maxDepth(t));
    }


    /**
     * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）

     例如：
     给定二叉树 [3,9,20,null,null,15,7],

     3
     / \
     9  20
     /  \
     15   7
     返回其自底向上的层次遍历为：

     [
     [15,7],
     [9,20],
     [3]
     ]
     * @param root
     * @return
     */
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        return levelOrderBottom(root,new LinkedList<List<Integer>>(),1);
    }

    public static List<List<Integer>> levelOrderBottom(TreeNode root,LinkedList<List<Integer>> result,int current){
        if(root != null) {
            List<Integer> leaf;
            if (current > result.size()) {
                leaf = new LinkedList<>();
                result.addFirst(leaf);
            } else {
                leaf = result.get(result.size() - current);

            }
            leaf.add(root.val);
            if (root.left != null) {
                levelOrderBottom(root.left, result, current + 1);
            }
            if (root.right != null) {
                levelOrderBottom(root.right, result, current + 1);
            }
        }
        return result;
    }

    public static void testlevelOrderBottom(){
        TreeNode t = new TreeNode(1);
        t.left=new TreeNode(2);
        t.right = new TreeNode(2);
        t.right.left = new TreeNode(4);
        t.right.right = new TreeNode(3);
        System.out.println(levelOrderBottom(t));
    }

    /**
     * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。

     本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

     示例:

     给定有序数组: [-10,-3,0,5,9],

     一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：

          0
         / \
       -3   9
       /   /
     -10  5
     * @param nums
     * @return
     */
    public static TreeNode sortedArrayToBST(int[] nums) {
        if(nums ==null || nums.length==0) return null;
        return sortedArrayToBST(new TreeNode(nums[((nums.length-1)/2)]),nums,0,nums.length-1);

    }

    public static TreeNode sortedArrayToBST(TreeNode root,int[] nums,int start,int end){
        int middle = (start+end)/2;
        int leftE = middle-1;
        int rightS = middle+1;
        int leftM = (start+leftE)/2;
        int rightM = (end+rightS)/2;
        if(leftE>=0){
            root.left = new TreeNode(nums[leftM]);
            sortedArrayToBST(root.left,nums,start,leftE);
        }
        if(rightS<=end){
            root.right = new TreeNode(nums[rightM]);
            sortedArrayToBST(root.right,nums,rightS,end);
        }

        return root;
    }

    public static void testSortedArrayToBST(){
        TreeNode treeNode = sortedArrayToBST(new int[]{0,1,2,3,4,5});
        System.out.println(levelOrderBottom(treeNode));
    }


    public TreeNode IntoTree(TreeNode root,int num,int leftH,int rightH){
        if(root ==null){
            root = new TreeNode(num);
            return root;
        }
        TreeNode temp = root;
        for(;;){
            if(temp.val>=num){
                if(temp.right==null){
                    temp.right = new TreeNode(num);
                    break;
                }else{
                    return IntoTree(temp.right,num);
                }
            }else{
                if(temp.left==null){
                    temp.left =  new TreeNode(num);
                    break;
                }else{
                    return IntoTree(temp.left,num);
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {
//        System.out.println(longestCommonPrefix(new String[]{"flower","flow","flight"}));
//        System.out.println(longestCommonPrefix(new String[]{"c","cc","ccc"}));
//        System.out.println(longestCommonPrefix(new String[]{"aca","cba"}));
//        System.out.println("abcde".substring(0,6));
//        Solution_1.Stack stack = new Solution_1().new Stack();
//        stack.add('a');
//        stack.add('b');
//        stack.add('c');
//        stack.add('d');
//        stack.prin();
//        removeElement(new int[]{1,1,1,1,1},0);
//        System.out.println(searchInsert(new int[]{1,3,5},3));
//        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
//        System.out.println(maxSubArray(new int[]{-1,1,2,1}));
//        System.out.println(maxSubArray(new int[]{3,-2,-3,-3,1,3,0}));
//        System.out.println(maxSubArray(new int[]{2,-3,1,3,-3,2,2,1}));
//        System.out.println(maxSubArray(new int[]{8,-2,-4,-1,-8,3,8,8,3,4,2,-9,-1,-3,-6,8,-3,9}));
//        System.out.println(lengthOfLastWord("Hello World"));
//        System.out.println(lengthOfLastWord("Hello World "));
//        System.out.println(lengthOfLastWord(" "));
//        System.out.println(lengthOfLastWord("a "));
//        String str = "asdfasd";
//        str.substring()
//        str.split()
//        []
//        System.out.println(addBinary("1010","1"));
//        deleteDuplicates(createListNode(new int[]{}));
//        deleteDuplicates(createListNode(new int[]{1,1,2,3,3}));
//        testlevelOrderBottom();
        testSortedArrayToBST();
        int[] a = {5,4,6,}
//        testMaxDepth();
//        System.out.println(1 ==0 ? true:false);




    }



}