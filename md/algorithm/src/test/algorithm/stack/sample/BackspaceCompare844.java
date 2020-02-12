package algorithm.stack.sample;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

public class BackspaceCompare844 {



    @Test
    public void testBackSpaceCompare(){
//        System.out.println("ffff");
        BackspaceCompare844 back = new BackspaceCompare844();
        String S="ab#c";
        String T = "ad#c";
        Assert.assertEquals(true,back.backSpaceCompare(S,T));

        String S2="ab##";
        String T2 = "c#d#";
        Assert.assertEquals(true,back.backSpaceCompare(S2,T2));
    }

    private boolean backSpaceCompare(String s, String t) {
        return build(s).equals(build(t));
    }

    private String build(String t) {
        Stack<Character> stack = new Stack<Character>();
        for(char c:t.toCharArray()){
            if(c!='#'){
                stack.push(c);
            }else if(!stack.empty()){
                stack.pop();
            }
        }
        return String.valueOf(stack);
    }

}
