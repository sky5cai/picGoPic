package algorithm.stack.sample;

import java.util.Stack;

public class MinStack {

    Stack<Integer> date ;
    Stack<Integer> helper ;

    public MinStack() {
        date = new Stack<Integer>();
        helper = new Stack<Integer>();
    }

    public void push(int x) {
        //先放原来的数据
        date.push(x);
        if(helper.empty() || helper.peek()<x){
            helper.push(x);
        }else{
            helper.push(helper.peek());
        }
    }

    public void pop() {
        if(!date.empty()){
            date.pop();
            helper.pop();
        }
    }

    public int top() {
        return date.peek();
    }

    public int getMin() {
        return helper.peek();
    }
}
