package com.company;

import com.company.exceptions.FullStackException;

import java.util.EmptyStackException;

public class ArrayStack<T> implements IStack<T>{

    T[] arr;
    int topElementIndex;

    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        arr = (T[])(new Object[capacity]);
        topElementIndex = 0;
    }

    @Override
    public boolean isEmpty() {
        return topElementIndex == 0;
    }

    @Override
    public boolean isFull() {
        return topElementIndex == arr.length;
    }

    @Override
    public T top() throws EmptyStackException {
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return arr[topElementIndex - 1];
    }

    @Override
    public T pop() throws EmptyStackException {
        if(isEmpty()){
            throw new EmptyStackException();
        }
        return arr[--topElementIndex];
    }

    @Override
    public void push(T value) throws FullStackException {
        if(isFull()){
            throw new FullStackException();
        }
        arr[topElementIndex++] = value;
    }

    @Override
    public int size() {
        return topElementIndex;
    }
}
