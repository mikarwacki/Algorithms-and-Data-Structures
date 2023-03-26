package com.company;

import com.company.exceptions.EmptyQueueException;
import com.company.exceptions.FullQueueException;
import com.company.exceptions.FullStackException;

import java.util.Stack;

public class Inverter {
    public static <T> IQueue<T> invert(IQueue<T> queue) throws EmptyQueueException, FullQueueException {
        ArrayStack<T> stack = new ArrayStack<>(queue.size());
        try {
            while (!queue.isEmpty()) {
                stack.push(queue.dequeue());
            }
            while(!stack.isEmpty()){
                queue.enqueue(stack.pop());
            }
            return queue;
        } catch (FullStackException e){
            return null;
        }
    }
}
