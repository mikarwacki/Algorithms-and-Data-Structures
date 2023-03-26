package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TwoWayLinkedList<T> implements IList<T> {
    private Node head;

    public class Node{
        private Node next;
        private Node previous;
        T data;

        public Node(T data){
            this.data = data;
        }
    }
    @Override
    public void add(T value) {
        Node nodeToAdd = new Node(value);
        nodeToAdd.next = null;
        Node current = head;
        if(current == null){
            nodeToAdd.previous = null;
            head = nodeToAdd;
            return;
        }
        while (current.next != null) {
            current = current.next;
        }
        current.next = nodeToAdd;
        nodeToAdd.previous = current;
    }

    @Override
    public void addAt(int index, T value) throws NoSuchElementException {
        Node nodeToBeAdded = new Node(value);
        Node current = head;
        int counter = 0;
        int placeForNewNode = index - 1;

        if(index < size() + 1) {
            if (index > 0) {
                if (head == null) {
                    head = nodeToBeAdded;
                    nodeToBeAdded.previous = null;
                }
                while (current != null && counter != placeForNewNode) {
                    current = current.next;
                    counter++;
                }
                nodeToBeAdded.next = current.next;
                current.next = nodeToBeAdded;
                nodeToBeAdded.previous = current;
            } else if (index == 0) {
                nodeToBeAdded.next = head;
                nodeToBeAdded.previous = null;
                head = nodeToBeAdded;
            } else {
                throw new NoSuchElementException();
            }
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void clear() {
        head = null;
    }

    @Override
    public boolean contains(T value) {
        Node current = head;
        while(current != null && current.data != value){
            current = current.next;
        }
        return current != null;
    }

    @Override
    public T get(int index) throws NoSuchElementException {
        Node current = head;
        int counter = 0;
        if(index >= 0) {
            while (current != null && counter != index) {
                current = current.next;
                counter++;
            }
            return current.data;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void set(int index, T value) throws NoSuchElementException {
        if(index < size()) {
            if (index >= 0) {
                Node current = head;
                int counter = 0;
                while (current != null && counter != index) {
                    current = current.next;
                    counter++;
                }
                current.data = value;
            } else {
                throw new NoSuchElementException();
            }
        } else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public int indexOf(T value) {
        Node current = head;
        int counter = 0;
        while(current != null && current.data != value){
            current = current.next;
            counter++;
        }
        if(current == null){
            return -1;
        } else {
            return counter;
        }
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public T removeAt(int index) throws NoSuchElementException {
        if(index < size() + 1) {
            if (index >= 0) {
                Node current = head;
                int counter = 0;
                if (index == 0) {
                    head = current.next;
                    return current.data;
                } else {
                    while (current != null && counter != index) {
                        current = current.next;
                        counter++;
                    }
                    current.previous.next = current.next;
                }
                if (current == null) {
                    return null;
                } else return current.data;
            } else {
                NoSuchElementException e = new NoSuchElementException();
                throw e;
            }
        } else{
            throw new NoSuchElementException();
        }
    }

    @Override
    public boolean remove(T value) {
        Node current = head;
        boolean isRemoved = true;
        if(head.data == value){
            head = current.next;
            return isRemoved;
        }
        while(current != null && current.data != value){
            current = current.next;
        }
        if(current == null){
            isRemoved = false;
        } else {
            current.previous.next = current.next;
        }
        return isRemoved;
    }

    @Override
    public int size() {
        Node current = head;
        int counter = 0;
        while(current != null){
            current = current.next;
            counter++;
        }
        return counter;
    }

    @Override
    public void print() {
        Node current = head;
        while(current != null){
            System.out.print(current.data + " ");
            current = current.next;
        }
    }

    public void distinct(){
        Node current = null, comparedNode = null;
        current = head;
        while (current != null && current.next != null) {
            comparedNode = current;
            while (comparedNode.next != null){
                if (current.data == comparedNode.next.data) {
                    comparedNode.next = comparedNode.next.next;
                }
                else {
                    comparedNode = comparedNode.next;
                }
            }
            current = current.next;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new TwoWayLinkedListIterator();
    }

    private class TwoWayLinkedListIterator implements Iterator<T> {
        Node current;

        public TwoWayLinkedListIterator(){
            current = head;
        }
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if(hasNext()){
                T data = current.data;
                current = current.next;
                return data;
            } else {
                throw new NoSuchElementException();
            }
        }
    }
}
