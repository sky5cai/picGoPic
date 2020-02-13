package algorithm.stack.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * [1003. 检查替换后的词是否有效](https://leetcode-cn.com/problems/check-if-word-is-valid-after-substitutions/)
 * 思想：
 * 如果是字符a或b直接入模拟栈stack，模拟栈大小index++
 * 如果是c,则前两个字符必须能组成"ab"，否则直接无效
 * 最后模拟栈大小index如果是0,则说明能消除完毕，有效，反之无效
 */
public class IsValid_stack1003 {

    @Test
    public void testMinAddToMakeValid(){
        String s="aabcbc";
        boolean isVaild = isValid_stack(s);

        Assert.assertEquals(true,isVaild);
    }

    private boolean isValid_stack(String S) {
        Stack<Character> stack = new Stack<Character>();
        for(char cs:S.toCharArray()){
            if(cs=='a' || cs=='b'){
                stack.push(cs);
            }else if(cs == 'c'){
                if(stack.size()<2 || stack.pop()!='b' || stack.pop()!='a'){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
