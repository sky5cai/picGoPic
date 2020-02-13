package algorithm.stack.medium;

import org.junit.Assert;
import org.junit.Test;

/**
 * [921. 使括号有效的最少添加](https://leetcode-cn.com/problems/minimum-add-to-make-parentheses-valid/)
 */
public class MinAddToMakeValid921 {

    @Test
    public void testMinAddToMakeValid(){
        String s="()))((";
        int number = minAddToMakeValid(s);

        Assert.assertEquals(4,number);
    }

    private int minAddToMakeValid(String S) {
        int ans = 0, bal = 0;
        for (int i = 0; i < S.length(); ++i) {
            bal += S.charAt(i) == '(' ? 1 : -1;
            // It is guaranteed bal >= -1
            if (bal == -1) {
                ans++;
                bal++;
            }
        }

        return ans + bal;
    }
}
