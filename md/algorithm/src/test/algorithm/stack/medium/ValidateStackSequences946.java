package algorithm.stack.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * [946. 验证栈序列](https://leetcode-cn.com/problems/validate-stack-sequences/)
 */
public class ValidateStackSequences946 {

    @Test
    public void validateStackSequencesTest(){
        int[] pushed = new int[]{1,2,3,4,5};
        int[] popped = new int[]{4,3,5,1,2};
        Assert.assertEquals(false,validateStackSequences(pushed,popped));
        int[] pushed2 = new int[]{1,2,3,4,5};
        int[] popped2 = new int[]{4,5,3,2,1};
        Assert.assertEquals(true,validateStackSequences(pushed2,popped2));

    }

    private boolean validateStackSequences(int[] pushed, int[] popped) {
        int N = pushed.length;
        Stack<Integer> stack = new Stack<Integer>();
        int j = 0;
        for(int x:pushed){
            stack.push(x);
            while(!stack.isEmpty() && j<N && stack.peek()==popped[j]){
                stack.pop();
                j++;
            }
        }
        return j==N;
    }
}
