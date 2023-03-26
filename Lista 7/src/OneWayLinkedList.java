import java.util.Iterator;
import java.util.NoSuchElementException;

public class OneWayLinkedList<T> implements IList<T> {
    private Node head;

    private class Node{
        Node next;
        T data;

        public Node(T data){
            this.data = data;
        }
    }

    @Override
    public void add(T value) {
        Node toBeAdded = new Node(value);
        toBeAdded.next = null;
        Node current = head;
        if(head == null){
            head = toBeAdded;
        } else {
            while (current.next != null) {
                current = current.next;
            }
            current.next = toBeAdded;
        }
    }

    @Override
    public void addAt(int index, T value) throws NoSuchElementException {
        Node nodeToBeAdded = new Node(value);
        Node current = head;
        int counter = 0;
        int placeForNewNode = index - 1;

        if(index > 0) {
            if (head == null) {
                head = nodeToBeAdded;
            }
            while (current != null && counter != placeForNewNode) {
                current = current.next;
                counter++;
            }
            nodeToBeAdded.next = current.next;
            current.next = nodeToBeAdded;
        } else if(index == 0) {
            nodeToBeAdded.next = head;
            head = nodeToBeAdded;
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
        if(index >= 0) {
            Node current = head;
            int counter = 0;
            while (current != null && counter != index) {
                current = current.next;
                counter++;
            }
            current.data = value;
        } else {
            NoSuchElementException e = new NoSuchElementException();
            throw e;
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
        if(index >= 0) {
            Node current = head;
            int counter = 0;
            Node prev = null;
            if (index == 0) {
                head = current.next;
                return current.data;
            } else {
                while (current != null && counter != index) {
                    prev = current;
                    current = current.next;
                    counter++;
                }
                prev.next = current.next;
            }
            if(current == null){
                return null;
            } else return current.data;
        }
        else {
            NoSuchElementException e = new NoSuchElementException();
            throw e;
        }
    }

    @Override
    public boolean remove(T value) {
        Node current = head;
        Node previous = current;
        boolean isRemoved = true;
        if(head.data == value){
            head = current.next;
            return isRemoved;
        }
        while(current != null && current.data != value){
            previous = current;
            current = current.next;
        }
        if(current == null){
            isRemoved = false;
        } else {
            previous.next = current.next;
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

    public void sortList(){
        Node current = head;
        Node nextNode = head;
        T temp = null;
        if(head == null){
            return;
        }
        while(current != null){
            nextNode = current.next;
            while(nextNode != null){
                if((Integer) current.data > (Integer) nextNode.data){
                    temp = current.data;
                    current.data = nextNode.data;
                    nextNode.data = temp;
                }
                nextNode = nextNode.next;
            }
            current = current.next;
        }
    }

    @Override
    public void print() {
        Node current = head;
        while(current != null){
            System.out.print(current.data + " ");
            current = current.next;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new OneWayLinkedListIterator();
    }

    private class OneWayLinkedListIterator implements Iterator<T> {
        Node current;

        public OneWayLinkedListIterator(){
            current = head;
        }
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (hasNext()) {
                T data = current.data;
                current = current.next;
                return data;
            } else {
                throw new NoSuchElementException();
            }
        }
    }
}
