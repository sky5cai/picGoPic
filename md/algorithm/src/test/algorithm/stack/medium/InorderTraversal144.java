package algorithm.stack.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class InorderTraversal144 {

    @Test
    public void preorderTraversalTest(){
        TreeNode root = new TreeNode(1);
        TreeNode right = new TreeNode(2);
        TreeNode threeLeft = new TreeNode(3);
        root.right = right;
        right.left = threeLeft;

        List<Integer> list = preorderTraversal(root);
        Assert.assertEquals(1,(int)list.get(0));
        Assert.assertEquals(3,(int)list.get(1));
        Assert.assertEquals(2,(int)list.get(2));

    }

    private List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        LinkedList<Integer> output = new LinkedList<Integer>();
        if(root == null){
            return output;
        }
//        stack.add(root);
        TreeNode curr = root;
        while(curr!=null || !stack.isEmpty()){
            while(curr!= null){
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            output.add(curr.val);
            curr = curr.right;
        }
        return output;
    }


}
