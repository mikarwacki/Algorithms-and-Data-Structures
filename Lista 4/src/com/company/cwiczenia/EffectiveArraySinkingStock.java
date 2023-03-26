package com.company.cwiczenia;

import com.company.IStack;
import com.company.exceptions.FullStackException;

import java.util.EmptyStackException;

public class EffectiveArraySinkingStock<T> implements IStack<T> {
    //czas stały, bo nie ma pętli w metodach
    //jedynie sprawdzanie if
    int beginning;
    int ending;
    T[] arr;

    @SuppressWarnings("unchecked")
    public EffectiveArraySinkingStock(int capacity){
        arr = (T[])(new Object [capacity]);
        beginning = 0;
        ending = 0;
    }

    @Override
    public boolean isEmpty() {
        //TODO
        return false;
    }

    @Override
    public boolean isFull() {
        //TODO
        return false;
    }

    @Override
    public T top() throws EmptyStackException {
        //TODO
        return null;
    }

    @Override
    public T pop() throws EmptyStackException {
        //TODO
        return null;
    }

    @Override
    public void push(T value) throws FullStackException {
        //TODO
    }

    @Override
    public int size() {
        //TODO
        return 0;
    }
}
