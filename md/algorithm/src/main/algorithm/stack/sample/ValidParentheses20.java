package algorithm.stack.sample;

import java.util.HashMap;
import java.util.Stack;

/**
 * [20. 有效的括号](https://leetcode-cn.com/problems/valid-parentheses/)
 * 思路：
 * 对应的()[]{}值放入map中，然后数组入栈，如果匹配map值则出栈，没匹配则出栈，最后返回的栈值是否为空，是则为正确
 */
public class ValidParentheses20 {

    private HashMap<Character,Character> mappings;

    //initialize

    public ValidParentheses20() {
        this.mappings = new HashMap<Character, Character>();
        mappings.put(')','(');
        mappings.put('}','{');
        mappings.put(']','[');
    }

    public boolean isValid(String s){
        //init stack
        Stack<Character> stack = new Stack<Character>();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            //if the current character is a closing bracket
            if(this.mappings.containsKey(c)){
                //pop stack
                char topElement = stack.empty()?'#':stack.pop();
                //if the mapping for this bracket doesn't matck the stack's top element,return false
                if(topElement!=this.mappings.get(c)){
                    return false;
                }
            }else{
                //if it was an opening bracket,push to the stack
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }
}
