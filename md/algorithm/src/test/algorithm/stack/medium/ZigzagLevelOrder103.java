package algorithm.stack.medium;

import org.junit.Test;

import java.util.*;

/**
 * [103. 二叉树的锯齿形层次遍历](https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/)
 */
public class ZigzagLevelOrder103 {

    @Test
    public void zigzagLevelOrderTest(){
        TreeNode root = new TreeNode(3);
        TreeNode right = new TreeNode(20);
        TreeNode left = new TreeNode(9);
        TreeNode threeLeft = new TreeNode(15);
        TreeNode rightLeft = new TreeNode(7);
        root.left = left;
        root.right = right;
        left.left = threeLeft;
        left.right = rightLeft;

        List<List<Integer>> lists = zigzagLevelOrder(root);
//        Assert.assertEquals(1,(int)list.get(0));
//        Assert.assertEquals(3,(int)list.get(1));
//        Assert.assertEquals(2,(int)list.get(2));
        System.out.println(lists);

    }

    private List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        Stack<TreeNode> evenLevel = new Stack<TreeNode>(); //记录偶数层的节点栈
        Stack<TreeNode> oddLevel = new Stack<TreeNode>(); //记录奇数层的节点栈
        evenLevel.push(root);
        for(int i=0;!evenLevel.isEmpty() || !oddLevel.isEmpty();i++){
            List<Integer> cur = new ArrayList<Integer>();
            if((i & 1) == 0){
                //偶数层，从0开始
                while(!evenLevel.isEmpty()){
                    TreeNode pop = evenLevel.pop();
                    if(pop != null){
                        cur.add(pop.val);
                        oddLevel.push(pop.left);
                        oddLevel.push(pop.right);
                    }
                }
            }else{
                //奇数层
                while(!oddLevel.isEmpty()){
                    TreeNode pop = oddLevel.pop();
                    if(pop!=null){
                        cur.add(pop.val);
                        evenLevel.push(pop.right);
                        evenLevel.push(pop.left);
                    }
                }
            }
            if(!cur.isEmpty()){
                res.add(cur);
            }
        }
        return res;
    }
}
