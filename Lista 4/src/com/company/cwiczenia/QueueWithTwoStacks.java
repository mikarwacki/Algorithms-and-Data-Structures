package com.company.cwiczenia;

import com.company.ArrayStack;
import com.company.IQueue;
import com.company.exceptions.EmptyQueueException;
import com.company.exceptions.FullQueueException;
import com.company.exceptions.FullStackException;

public class QueueWithTwoStacks<T> implements IQueue<T> {
    ArrayStack<T> firstStack;
    ArrayStack<T> secondStack;
    int maxSize;

    @Override
    public boolean isEmpty() {
        return (firstStack.isEmpty() && secondStack.isEmpty());
    }

    @Override
    public boolean isFull() {
        return size() >= maxSize;
    }

    @Override
    public void enqueue(T value) throws FullQueueException {
        try {
            firstStack.push(value);
        } catch(FullStackException e){
        }
    }

    @Override
    public T first() throws EmptyQueueException {
        return null;
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        if(secondStack.isEmpty()){
            //push all elements from stack one to stack two
        }
        T temp = secondStack.pop();
        return temp;
    }

    @Override
    public int size() {
        return firstStack.size() + secondStack.size();
    }
}
