package ja.leetcode;

import ja.leetcode.annotation.Title;

public class GreedySolution {

    @Title(title="盛最多水的容器",num="11",tags={"贪心","数组","双指针"})
    public static int maxArea(int[] height) {
        int a=0,b = height.length-1;
        int result =0;
        while(b>a){
            int i2 = Math.min(height[a], height[b]) * (b - a);
            result = Math.max(i2,result);
            if(height[a]>height[b]){
                b--;
            }else{
                a++;
            }
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
