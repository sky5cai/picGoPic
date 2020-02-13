package algorithm.stack.sample;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

public class NextGreaterElements503 {
    @Test
    public void testNextGreaterElements(){
        int[] ints = nextGreaterElements(new int[]{1, 2,1});
        Assert.assertEquals(2,ints[0]);
        Assert.assertEquals(-1,ints[1]);
        Assert.assertEquals(2,ints[2]);
    }

    private int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<Integer>();
        for(int i=2*nums.length-1;i>=0;--i){
            while(!stack.empty() && nums[stack.peek()]<=nums[i%nums.length]){
                stack.pop();
            }
            res[i%nums.length] = stack.empty() ?-1:nums[stack.peek()];
            stack.push(1%nums.length);
        }

        return res;
    }
}
