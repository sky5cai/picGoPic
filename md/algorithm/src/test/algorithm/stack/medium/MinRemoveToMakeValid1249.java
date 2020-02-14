package algorithm.stack.medium;

import com.sun.org.apache.bcel.internal.generic.POP;
import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * [1249. 移除无效的括号](https://leetcode-cn.com/problems/minimum-remove-to-make-valid-parentheses/)
 *
 */
public class MinRemoveToMakeValid1249 {

    @Test
    public void minRemoveToMakeValidTest(){
        String s="a)b(c)d";
//        Assert.assertEquals("ab(c)d",minRemoveToMakeValid(s));

        String s2="lee(t(c)o)de)";
        Assert.assertEquals("lee(t(c)o)de",minRemoveToMakeValid(s2));

        String s3="))((";
//        Assert.assertEquals("",minRemoveToMakeValid(s3));

        String s4="(a(b(c)d)";
//        Assert.assertEquals("a(b(c)d)",minRemoveToMakeValid(s4));

    }

    private String minRemoveToMakeValid(String s) {
        //栈 放入指定位置
        Stack<Integer> stack = new Stack<Integer>();
        StringBuilder sb = new StringBuilder();
        boolean[] inVaildIndex = new boolean[s.length()];
        char[] chars = s.toCharArray();
        for(int i=0;i<chars.length;i++){
            //如果ch元素为(,),则入栈且记录位置信息
            //如果为(,入栈，记录（位置，并记录无效数组为true，如果为),则出栈，并记录无效为false
            if(chars[i]=='('){
                stack.push(i);
                inVaildIndex[i]=true;
            }else if(chars[i]==')') {
                if(stack.empty()){
                    inVaildIndex[i] = true;
                }else{
                    inVaildIndex[stack.pop()] = false;
                }
            }
        }

        for(int i=0;i<chars.length;i++){
            if(!inVaildIndex[i]){
                sb.append(chars[i]);
            }
        }

        return sb.toString();
    }
}
