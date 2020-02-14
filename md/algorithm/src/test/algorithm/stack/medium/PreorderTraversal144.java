package algorithm.stack.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class PreorderTraversal144 {

    @Test
    public void preorderTraversalTest(){
        TreeNode root = new TreeNode(1);
        TreeNode right = new TreeNode(2);
        TreeNode threeLeft = new TreeNode(3);
        root.right = right;
        right.left = threeLeft;

        List<Integer> list = preorderTraversal(root);
        Assert.assertEquals(1,(int)list.get(0));
        Assert.assertEquals(2,(int)list.get(1));
        Assert.assertEquals(3,(int)list.get(2));

    }

    private List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        LinkedList<Integer> output = new LinkedList<Integer>();
        if(root == null){
            return output;
        }
        stack.add(root);
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            output.add(node.val);
            if(node.right !=null)
                stack.add(node.right);
            if(node.left !=null)
                stack.add(node.left);
        }
        return output;
    }


}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}
