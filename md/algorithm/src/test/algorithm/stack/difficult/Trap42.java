package algorithm.stack.difficult;

import org.junit.Assert;
import org.junit.Test;

public class Trap42 {

    @Test
    public void testMinAddToMakeValid(){
        int[] tokens = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        int number = trap(tokens);

        Assert.assertEquals(6,number);
    }

    private int trap(int[] height) {
        int sum = 0;
        //最两端的列不用考虑，因为一定不会有水，所以下标从1到length-2
        for(int i=1;i<height.length-1;i++){
            int max_left = 0;
            //找出左边最高
            for(int j=i-1;j>=0;j--){
                if(height[j] > max_left){
                    max_left = height[j];
                }
            }
            int max_right = 0;
            //找出右边最高
            for(int j=i+1;j<height.length;j++){
                if(height[j] > max_right){
                    max_right = height[j];
                }
            }
            //找出两端较小的
            int min = Math.min(max_left,max_right);
            //只有较小的一段大于当前列的高度才会有水，其他情况不会有水
            if(min>height[i]){
                sum = sum + (min-height[i]);
            }
        }
        return sum;
    }
}
