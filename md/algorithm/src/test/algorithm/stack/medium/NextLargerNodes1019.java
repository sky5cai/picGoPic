package algorithm.stack.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * [1019. 链表中的下一个更大节点](https://leetcode-cn.com/problems/next-greater-node-in-linked-list/)
 * 思路：
 * 从链表中拿出来，做比较，其中stack放指标值，valueArr用来放数组值
 */
public class NextLargerNodes1019 {


    @Test
    public void nextLargerNodesTest(){
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(1);
        ListNode listNode3 = new ListNode(5);
        listNode1.next = listNode2;
        listNode2.next = listNode3;

        int[] listNodeTemp = nextLargerNodes(listNode1);
        Assert.assertEquals(5,listNodeTemp[0]);
        Assert.assertEquals(5,listNodeTemp[1]);
        Assert.assertEquals(0,listNodeTemp[2]);


    }

    private int[] nextLargerNodes(ListNode head) {
        int num = 0;
        ListNode temp = head;
        while(temp!=null ){
            num++;
            temp = temp.next;
        }
        int[] arr = new int[num];
        int[] valueArr = new int[num];
        Stack<Integer> stack = new Stack<Integer>();
        int length = 0;
        int value;
        while(head!=null){
            value = head.val;
            valueArr[length] = value;
            while(!stack.isEmpty() && value>valueArr[stack.peek()]){
                arr[stack.pop()] = value;
            }
            stack.add(length);
            length++;
            head = head.next;
        }

        return arr;
    }


}

class ListNode{
    public int val;
    public ListNode next;
    public ListNode(int x){
        val=x;
    }
}
