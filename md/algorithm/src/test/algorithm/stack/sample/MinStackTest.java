package algorithm.stack.sample;

import org.junit.Test;

public class MinStackTest {

    @Test
    public void minStack(){
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        minStack.getMin();
        minStack.pop();
        minStack.top();
        minStack.getMin();

    }
}
