package algorithm.stack.sample;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * [856. 括号的分数](https://leetcode-cn.com/problems/score-of-parentheses/)
 */
public class ScoreOfParentheses856 {
    @Test
    public void testScoreOfParentheses(){
//        int[] ints = dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
//        Assert.assertEquals(1,ints[0]);
//        Assert.assertEquals(1,ints[1]);
//        Assert.assertEquals(4,ints[2]);
//        Assert.assertEquals(2,ints[3]);
//        Assert.assertEquals(1,ints[4]);
//        Assert.assertEquals(1,ints[5]);
//        Assert.assertEquals(0,ints[6]);
        String s="(()(()))";
        int number = scoreOfParentheses(s);
        Assert.assertEquals(6,number);

        String s1="(())";
        int number1 = scoreOfParentheses(s1);
        Assert.assertEquals(2,number1);

    }

    private int scoreOfParentheses(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);
        for(char c:s.toCharArray()){
            if(c=='(')
                stack.push(0);
            else{
                int v = stack.pop();
                int w = stack.pop();
                stack.push(w+Math.max(2*v,1));
            }
        }
        return stack.pop();
    }


}
