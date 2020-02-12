package algorithm.stack.sample;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Stack;

public class NextLargerNumberTest {

    public int[] nextGreaterElement(int[] findNums,int[] nums){
        Stack<Integer> stack = new Stack<Integer>();
        HashMap<Integer,Integer> map = new HashMap<Integer, Integer>();
        int[] res = new int[findNums.length];
        for(int i=0;i<nums.length;i++){
            while(!stack.empty() && nums[i]>stack.peek()){
                map.put(stack.pop(),nums[i]);
            }
            stack.push(nums[i]);
        }
        while(!stack.empty())
            map.put(stack.pop(),-1);
        for(int i=0;i<findNums.length;i++){
            res[i] = map.get(findNums[i]);
        }
        return res;
    }

    @Test
    public void testNextLargerNumber(){
        int[] ints = nextGreaterElement(new int[]{2, 4}, new int[]{1, 2, 3, 4});
        Assert.assertEquals(3,ints[0]);
        Assert.assertEquals(-1,ints[1]);
    }
}
