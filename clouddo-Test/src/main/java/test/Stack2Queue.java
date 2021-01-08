package test;


import java.util.Stack;

public class Stack2Queue {
    // 作为入队序列
    private Stack<Integer> stack1 = new Stack<Integer>();
    // 作为出队序列
    private Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        // 入队时，要保证stack2为空
        while (!stack2.empty())
        {
            stack1.push(stack2.peek());
            stack2.pop();
        }
        stack1.push(node);
        System.out.println("入队元素是:" + stack1.peek());
    }

    public int pop() {
        // 出队时，要保证stack1为空
        while (!stack1.empty())
        {
            stack2.push(stack1.peek());
            stack1.pop();
        }
        System.out.println("出队元素是:" + stack2.peek());
        int temp = stack2.peek();
        stack2.pop();
        return temp;}

    int exchange(int x,int y)
    {
       Stack<Integer> stack1 = new Stack<Integer>();
        stack1.push(x);
        push(y);
        x=stack1.pop();
        y=stack1.pop();
        return 0;
    }
}

