package algorithm.stack.sample;

import org.junit.Assert;
import org.junit.Test;

/**
 * [1021. 删除最外层的括号](https://leetcode-cn.com/problems/remove-outermost-parentheses/)
 */
public class RemoveOuterParentheses1021 {

    @Test
    public void removeOuterParenteseTest(){
        String s = "(()())(())(()(()))";
        s = removeOuterParentese(s);
        Assert.assertEquals(s,"()()()()(())");
//        Assert.assertEquals(-1,ints[1]);
    }

    private String removeOuterParentese(String s) {
        StringBuilder sb = new StringBuilder();
        int level = 0;
        for(char c:s.toCharArray()){
            if(c==')') --level;
            if(level>=1) sb.append(c);
            if(c=='(') ++level;
        }
        return sb.toString();
    }
}
