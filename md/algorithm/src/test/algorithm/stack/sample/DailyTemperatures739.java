package algorithm.stack.sample;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

public class DailyTemperatures739 {
    @Test
    public void testdailyTemperatures(){
//        int[] ints = dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
//        Assert.assertEquals(1,ints[0]);
//        Assert.assertEquals(1,ints[1]);
//        Assert.assertEquals(4,ints[2]);
//        Assert.assertEquals(2,ints[3]);
//        Assert.assertEquals(1,ints[4]);
//        Assert.assertEquals(1,ints[5]);
//        Assert.assertEquals(0,ints[6]);

        int[] ints2 = dailyTemperatures(new int[]{89,62,70,58,47,47,46,76,100,70});
        Assert.assertEquals(8,ints2[0]);
        Assert.assertEquals(1,ints2[1]);
        Assert.assertEquals(5,ints2[2]);
        Assert.assertEquals(4,ints2[3]);
        Assert.assertEquals(3,ints2[4]);
        Assert.assertEquals(2,ints2[5]);
        Assert.assertEquals(1,ints2[6]);
        Assert.assertEquals(1,ints2[7]);
        Assert.assertEquals(0,ints2[8]);
        Assert.assertEquals(0,ints2[9]);
    }

    private int[] dailyTemperatures(int[] T) {
        Stack<Integer> stack = new Stack<Integer>();
        int[] res = new int[T.length];
        for(int i=0;i<T.length;i++){
            //栈不为空且元素大于栈里面的元素
            while(!stack.empty() && T[i]>T[stack.peek()]){
                res[stack.peek()] = i-stack.pop();
            }

            stack.push(i);
        }

        return res;
    }
}
