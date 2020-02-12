package algorithm.stack.sample;

import java.util.LinkedList;
import java.util.Queue;

public class MyStackByQueue {

    private Queue<Integer> a;//输入队列
    private Queue<Integer> b;//输出队列

    public MyStackByQueue() {
        a = new LinkedList<Integer>();
        b = new LinkedList<Integer>();
    }

    public void push(int x){
        a.offer(x);
        //将b队列中元素全部转给a队列
        while(!b.isEmpty()){

        }
    }
}
