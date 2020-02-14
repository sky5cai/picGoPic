package algorithm.stack.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * [1209. 删除字符串中的所有相邻重复项 II](https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string-ii/)
 * 一个栈放元素，一个栈放重复次数，如果达到一定次数后，出栈
 */
public class RemoveDuplicates_stack1209 {

    @Test
    public void reverseParenthesesTest(){
        String s="deeedbbcccbdaa";
        Assert.assertEquals("aa",removeDuplicates_stack(s,3));

    }

    private String removeDuplicates_stack(String s,int k) {
        Stack<Character> chStack = new Stack<Character>();
        Stack<Integer> numStack = new Stack<Integer>();
        for(char ch: s.toCharArray()){
            //栈为空或者ch和栈元素不相同，入栈，并初始化联系出现的数量为1
            if(chStack.isEmpty() || chStack.peek()!=ch){
                chStack.push(ch);
                numStack.push(1);
            }else if(chStack.peek() == ch){
                //字符栈顶元素已连续出现k-1次，且加上当前字符后满足连续k个，2个栈顶都出栈
                if(numStack.peek()+1 == k){
                    numStack.pop();
                    chStack.pop();
                }else{
                    //与字符栈顶元素相同但未满足k，计数栈栈顶加1
                    numStack.push(numStack.pop()+1);
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!numStack.isEmpty()){
            int repeat = numStack.pop();
            char repeatCh = chStack.pop();
            for(int i=0;i<repeat;i++){
                sb.insert(0,repeatCh);
            }
        }
        return sb.toString();
    }
}
