package com.company.cwiczenia;

import com.company.ArrayStack;
import com.company.exceptions.FullStackException;

import java.sql.Array;

public class ReverseStack<T> extends ArrayStack<T> {

    public ReverseStack(int capacity) {
        super(capacity);
    }

    public void reverse(int capacity) throws FullStackException {
        ArrayStack<T> otherStack = new ArrayStack<>(capacity);
        ArrayStack<T> secondStack = new ArrayStack<>(capacity);
        int n = size();
        for(int i = 0; i < n; i++){
            T temp = pop();
            for(int j = 0; j < n - i -1; j++){
                otherStack.push(pop());
            }
            while(!otherStack.isEmpty()){
                push(otherStack.pop());
            }
        }
    }
}
