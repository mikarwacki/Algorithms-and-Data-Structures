package com.company;

import com.company.exceptions.*;

import java.util.LinkedList;
import java.util.Queue;

public class TwoWayLinkedListQueue<T> implements IQueue<T> {

    TwoWayLinkedList<T> list;
    int capacity;
    
    public TwoWayLinkedListQueue(int capacity) {
        list = new TwoWayLinkedList<>();
        this.capacity = capacity;
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean isFull() {
        return list.size() >= capacity;
        //złożoność liniowa TODO
    }

    @Override
    public void enqueue(T value) throws FullQueueException {
        if(isFull()){
            throw new FullQueueException();
        }
        list.add(value);
    }

    @Override
    public T first() throws EmptyQueueException {
        if(isEmpty()){
            throw new EmptyQueueException();
        }
        return list.get(0);
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        if(isEmpty()){
            throw new EmptyQueueException();
        }
        T data = list.removeAt(0);
        return data;
    }

    @Override
    public int size() {
        return list.size();
    }
}
