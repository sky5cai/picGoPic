package algorithm.stack.difficult;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

public class LargestRectangleArea84 {

    @Test
    public void testLargestRectangleArea(){
        int[] heights = new int[]{2,1,5,6,2,3};
        int number = largestRectangleArea(heights);
        Assert.assertEquals(10,number);

        int number2 = largestRectangleArea2(heights);
        Assert.assertEquals(10,number2);

    }

    private int largestRectangleArea(int[] heights) {
        int maxarea = 0;
        for(int i=0;i<heights.length;i++){
            for(int j=i;j<heights.length;j++){
                int minheight = Integer.MAX_VALUE;
                for(int k=i;k<=j;k++)
                    minheight = Math.min(minheight,heights[k]);
                maxarea = Math.max(maxarea,minheight*(j-i+1));
            }
        }
        return maxarea;
    }

    public int largestRectangleArea2(int[] heights) {
        Stack< Integer > stack = new Stack <Integer>();
        stack.push(-1);
        int maxarea = 0;
        for (int i = 0; i < heights.length; ++i) {
            while (stack.peek() != -1 && heights[stack.peek()] >= heights[i])
                maxarea = Math.max(maxarea, heights[stack.pop()] * (i - stack.peek() - 1));
            stack.push(i);
        }
        while (stack.peek() != -1)
            maxarea = Math.max(maxarea, heights[stack.pop()] * (heights.length - stack.peek() -1));
        return maxarea;
    }
}
