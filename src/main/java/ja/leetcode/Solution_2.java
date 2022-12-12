package ja.leetcode;

import ja.leetcode.annotation.Title;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution_2 {

    @Title(title="无重复字符的最长子串",num="3",tags={"哈希表","字符串","滑动窗口"})
    public static int lengthOfLongestSubstring(String s){

        int[] last = new int[128];
        for(int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        int n = s.length();

        int res = 0;
        int start = 0; // 窗口开始位置
        for(int i = 0; i < n; i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] +1);
            res   = Math.max(res, i - start + 1);
            last[index] = i;
        }

        return res;
    }

    @Title(title="数组中重复的数字",num="剑指 Offer 03",tags={"哈希表","数组","字符串"})
    public static int findRepeatNumber(int[] nums) {
        int[] temp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if(temp[nums[i]]>0){
                return nums[i];
            }
            temp[nums[i]]=1;
        }
        return -1;
    }

    @Title(title="二维数组中的查找",num="剑指 Offer 04",tags={"哈希表","数组","字符串"})
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        for (int i = 0; i < matrix.length; i++) {
            int[] temp = matrix[i];
            int head = temp[0];
            int tail = temp[temp.length-1];
            if(!(target>=head && target<=tail)){
                continue;
            }
            if(Arrays.binarySearch(temp,target)>0){
                return true;
            }
        }
        return false;
    }

    @Title(title="数值的整数次方",num="剑指 Offer 16",tags={"递归","数学"})
    public static double myPow(double x, int n) {
        if(x == 0) return 0;
        long b = n;
        double res = 1.0;
        if(b < 0) {
            x = 1 / x;
            b = -b;
        }
        while(b > 0) {
            if((b & 1) == 1){
                res *= x;
            }
            x *= x;
            b >>= 1;
        }
        return res;
    }

    public void test(int[][] ints){
        int l = ints.length;
        int k = ints[0].length;

        for (int i = 0,t=0; t < k; t++) {
            System.out.println(ints[i][t]);
            if(t+1==k){

            }
//            if(i+1==)
        }

    }

    @Title(title="增量元素之间的最大差值",num="2016",tags={"数组"})
    public static int maximumDifference(int[] nums) {
        int n = nums.length;
        int ans = -1, premin = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > premin) {
                ans = Math.max(ans, nums[i] - premin);
            } else {
                premin = nums[i];
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] ints = new int[]{87,68,91,86,58,63,43,98,6,40};
        System.out.println(maximumDifference(ints));
    }
}
