package algorithm.stack.medium;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

public class ReverseParentheses1190 {

    @Test
    public void reverseParenthesesTest(){
        String s="(u(love)i)";
        Assert.assertEquals("iloveu",reverseParentheses(s));

    }

    private String reverseParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        char[] arr = s.toCharArray();
        Stack<Integer> stack = new Stack<Integer>();
        for(int i=0;i<arr.length;i++){
            if(arr[i] == '(')
                stack.push(i);
            if(arr[i] == ')')
                reverse(arr,stack.pop()+1,i-1);
        }
        for(int i=0;i<arr.length;i++){
            if(arr[i]!=')' && arr[i] != '(')
                sb.append(arr[i]);
        }
        return sb.toString();
    }

    private void reverse(char[] arr, int left, int right) {
        while(right>left){
            char tmp = arr[left];
            arr[left] = arr[right];
            arr[right] = tmp;
            right--;
            left++;
        }
    }
}
