package ja.leetcode;

import ja.leetcode.annotation.Title;
import sun.reflect.generics.tree.Tree;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class TreeSolution {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static boolean isBalanced(TreeNode root) {
        int right = level(root.left);
        int left = level2(root.right);
        if (Math.abs(right - left) > 1) {
            return false;
        }
        return true;
    }

    public static int level(TreeNode root) {
        if (root.left == null) {
            return 0;
        } else {
            return level(root.left) + 1;
        }
    }

    public static int level2(TreeNode root) {
        if (root.right == null) {
            return 0;
        } else {
            return level2(root.right) + 1;
        }
    }



    /**
     * 根据数组打印出一颗平衡树
     * 二叉树 [3,9,20,null,null,15,7]
     *     3
     *    / \
     *   9  20
     *     /  \
     *    15   7
     * @param ints
     */
    public static TreeNode format(Integer[] ints) {
        if (ints.length == 0) {
            return null;
        }
        if (null == ints[0]) {
            return null;
        }
        TreeNode root = new TreeNode(ints[0]);
        format(ints, 0, root);
        return root;

    }

    /**
     * 假设根节点位置为n，与左右节点的关系为：2 * n + 1 和 2 * (n + 1)
     * @param ints
     * @param n
     * @param root
     * @return
     */
    public static TreeNode format(Integer[] ints, int n, TreeNode root) {
        int leftPos = 2 * n + 1;
        //左子树位置超过长度，直接返回
        if (ints.length - 1 < leftPos) {
            return root;
        }
        if(ints[leftPos] != null){
            root.left = new TreeNode(ints[leftPos]);
            format(ints,leftPos,root.left);
        }

        int rightPos = 2 * (n + 1);
        //右子树子树位置超过长度，直接返回
        if (ints.length - 1 < rightPos) {
            return root;
        }
        if(ints[rightPos] != null){
            root.right = new TreeNode(ints[rightPos]);
            format(ints,rightPos,root.right);
        }
        return root;
    }

    /**
     * 二叉树中序遍历，递归
     * @param root
     * @param list
     * @return
     */
    @Title(title="二叉树的中序遍历",num="94",tags={"栈","树","深度优先搜索","二叉树"})
    public static List<Integer> inorderTraversal(TreeNode root, List<Integer> list) {
        if(root==null) return list;
        inorderTraversal(root.left,list);
        list.add(root.val);
        inorderTraversal(root.right,list);
        return list;
    }

    /**
     * 二叉树中序遍历
     * @param root
     * @return
     */
    @Title(title="二叉树的中序遍历",num="94",tags={"栈","树","深度优先搜索","二叉树"})
    public static List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new LinkedList<>();
        while(root!=null || stack.size()>0){
            if(root!=null){
                stack.add(root);
                root=root.left;
            }else{
                root = stack.pop();
                list.add(root.val);
                if(root.right!=null){
                    root = root.right;
                }else{
                    root = null;
                }
            }
        }
        return list;
    }
    /**
     *二叉树的后序遍历，递归
     * @param root
     * @return
     */
    @Title(title="二叉树的后序遍历",num="145",tags={"栈","树","深度优先搜索","二叉树"})
    public List<Integer> postorderTraversal(TreeNode root, List<Integer> list) {
        if(root==null) return list;
        postorderTraversal(root.left,list);
        postorderTraversal(root.right,list);
        list.add(root.val);
        return list;
    }

    /**
     *二叉树的后序遍历
     * @param root
     * @return
     */
    @Title(title="二叉树的后序遍历",num="145",tags={"栈","树","深度优先搜索","二叉树"})
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new LinkedList<>();
        TreeNode r = null;
        while(root!=null || stack.size()>0){
            if(root!=null){
                stack.add(root);
                root=root.left;
            }else{
                root = stack.peek();
                if(root.right==null ||  root.right == r){
                    list.add(stack.pop().val);
                    r=root;
                    root = null;
                }else{
                    root = root.right;
                }
            }
        }
        return list;
    }

    /**
     * 二叉树前序遍历，递归
     * @param root
     * @param list
     * @return
     */
    @Title(title="二叉树的前序遍历",num="144",tags={"栈","树","深度优先搜索","二叉树"})
    public static List<Integer> preorderTraversal(TreeNode root, List<Integer> list){
        if(root!=null){
            list.add(root.val);
            preorderTraversal(root.left,list);
            preorderTraversal(root.right,list);
        }
        return list;
    }

    /**
     * 二叉树前序遍历,
     * @param root
     * @return
     */
    @Title(title="二叉树的前序遍历",num="144",tags={"栈","树","深度优先搜索","二叉树"})
    public static List<Integer> preorderTraversal(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new LinkedList<>();
        while(root!=null || !stack.isEmpty()){
            if(root!=null){
                list.add(root.val);
                if(root.right!=null){
                    stack.add(root.right);
                }
                root = root.left;
            }else{
                root = stack.pop();
            }
        }
        return list;
    }

    /**
     * 二叉树的层序遍历
     * @param root
     * @return
     */
    @Title(title="二叉树的层序遍历",num="102",tags={"树","广度优先搜索","二叉树"})
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if(root==null) return result;
        LinkedList<TreeNode> temp = new LinkedList<>();
        temp.add(root);
        while(temp.size()>0){
            LinkedList<TreeNode> temp2 = new LinkedList<>();
            List<Integer> re = new LinkedList<>();
            for (TreeNode node : temp) {
                re.add(node.val);
                if(node.right !=null){
                    temp2.add(node.right);
                }
                if(node.left!=null){
                    temp2.add(node.left);
                }
            }
            result.add(re);
            temp = temp2;
        }
        return result;
    }

    /**
     *
     * @param grid
     * @return
     */
    @Title(title="最短的桥",num="934",tags={"矩阵","数组","广度优先搜索","二叉树"})
    public int shortestBridge(int[][] grid) {
        return 0;
    }

    @Title(title="完全二叉树的节点个数",num="222",tags={"树","二分查找","深度优先搜索","二叉树"})
    public int countNodes(TreeNode root) {
        TreeNode temp = root;
        int num=0;
        while(root!=null){
            if(root.left!=null){
                root = root.left;
            }
            if(root.right!=null){
                root = root.right;
            }
            num++;
        }
        return num;
    }

    @Title(title="二叉树的右视图",num="199",tags={"树","广度优先搜索","深度优先搜索","二叉树"})
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if(root==null) return result;
        ArrayList<TreeNode> temp = new ArrayList<>();
        temp.add(root);
        while(temp.size()>0){
            ArrayList<TreeNode> temp2 = new ArrayList<>();
//            List<Integer> re = new LinkedList<>();
            for (int i = 0; i < temp.size(); i++) {
                TreeNode node = temp.get(i);
                if(i==temp.size()-1){
                    result.add(node.val);
                }
                if(node.left!=null){
                    temp2.add(node.left);
                }
                if(node.right !=null){
                    temp2.add(node.right);
                }

            }
            temp = temp2;
        }
        return result;
    }

    public static void main(String[] args) throws NamingException {
//        Integer[] ints = new Integer[]{1,2,3,null,5,null,4};
////        TreeNode format = format(ints);
////        System.out.println(rightSideView(format));
        Object lookup = new InitialContext().lookup("java:comp/env");
        System.out.println();

    }

}
