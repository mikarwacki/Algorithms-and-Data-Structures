import java.util.NoSuchElementException;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.fail;

public class HashMap<TKey, TValue> {
    private LinkedElements[] data;
    private double loadFactor;
    private Function<TKey, Integer> hashFunction;

    private class LinkedElements<TKey, TValue> {
        TKey key;
        TValue value;
        LinkedElements next;

        LinkedElements(TKey key, TValue value){
            this.value = value;
            this.key = key;
            this.next = null;
        }
    }

    public HashMap(int initialSize, double loadFactor, Function<TKey, Integer> hashFunction) {
        this.data = new LinkedElements[initialSize];
        this.loadFactor = loadFactor;
        this.hashFunction = hashFunction;
    }

    public void add(TKey key, TValue value) throws DuplicateKeyException {
        if(containsKey(key)){
            throw new DuplicateKeyException();
        } else {
            checkLoadFactor();
            int index = hashFunction.apply(key);
            LinkedElements element = new LinkedElements(key, value);
            if(data[index] == null){
                data[index] = element;
            } else {
                LinkedElements inputed = data[index];
                while(inputed != null){
                    inputed = inputed.next;
                }
                inputed.next = element;
            }
        }
    }

    public void clear() {
        for(int i = 0; i < data.length; i++){
            data[i] = null;
        }
    }

    public boolean containsKey(TKey key) {
        int index = hashFunction.apply(key);
        LinkedElements element = data[index];
        if(element == null){
            return false;
        } else {
            while (element != null && element.key != key) {
                element = element.next;
            }
            if (element == null) {
                return false;
            } else {
                return true;
            }
        }
    }

    public boolean containsValue(TValue value) {
        boolean contains = true;
        for(int i = 0; i < data.length; i++){
            LinkedElements element = data[i];
            while(element != null && !element.value.equals(value)){
                element = element.next;
            }
            if(element == null){
                contains = false;
            } else {
                contains = true;
                break;
            }
        }
        return contains;
    }

    public int elements() {
        int counter = 0;
        for(int i = 0; i < data.length; i++){
            LinkedElements element = data[i];
            while (element != null){
                counter++;
                element = element.next;
            }
        }
        return counter;
    }

    public TValue get(TKey key) throws NoSuchElementException {
        if(containsKey(key)){
            int index = hashFunction.apply(key);
            LinkedElements element = data[index];
            while(element != null && element.key != key){
                element = element.next;
            }
            return (TValue) element.value;
        } else {
            throw new NoSuchElementException();
        }
    }

    public void put(TKey key, TValue value) {
        if (containsKey(key)) {
            int index = hashFunction.apply(key);
            LinkedElements element = data[index];
            while(element != null && element.key != key){
                element = element.next;
            }
            element.value = value;
        } else {
            checkLoadFactor();
            int index = hashFunction.apply(key);
            LinkedElements element = new LinkedElements(key, value);
            if(data[index] == null){
                data[index] = element;
            } else {
                LinkedElements inputed = data[index];
                while(inputed != null){
                    inputed = inputed.next;
                }
                inputed.next = element;
            }
        }
    }

    public TValue remove(TKey key) {
        int index = hashFunction.apply(key);
        LinkedElements current = data[index];
        LinkedElements previous = current;
        if(current == null){
            return null;
        }
        if(current.key == key){
            data[index] = current.next;
            return (TValue) current.value;
        }
        while(current != null && current.key != key){
            previous = current;
            current = current.next;
        }
        if(current == null){
            return null;
        } else {
            previous.next = current.next;
        }
        return (TValue) current.value;
    }

    public int size() {
        return data.length;
    }

    public void checkLoadFactor(){
        double currentLoadFactor = (double) (elements() + 1) / size();
        if(loadFactor <= currentLoadFactor){
            int newSize = data.length * 2;
            LinkedElements[] temp = new LinkedElements[newSize];
            for(int i = 0; i < data.length; i++) {
                if(data[i] == null){
                    continue;
                }
                LinkedElements element = data[i];
                while(element != null) {
                    int newIndex = hashFunction.apply((TKey) element.key);
                    if (temp[newIndex] == null) {
                        temp[newIndex] = data[i];
                    } else {
                        LinkedElements tempElement = temp[newIndex];
                        while (tempElement.next != null) {
                            tempElement = tempElement.next;
                        }
                        temp[newIndex].next = data[i];
                    }
                    element = element.next;
                }
            }
            data = temp;
        }
    }

    public void rehash(Function<TKey, Integer> newHashFunction) {
        this.hashFunction = newHashFunction;
        LinkedElements[] temp = new LinkedElements[data.length];
        for(int i = 0; i < data.length; i++) {
            if(data[i] == null){
                continue;
            }
            LinkedElements element = data[i];
            while(element != null) {
                int newIndex = newHashFunction.apply((TKey) element.key);
                if (temp[newIndex] == null) {
                    temp[newIndex] = data[i];
                } else {
                    LinkedElements tempElement = temp[newIndex];
                    while (tempElement.next != null) {
                        tempElement = tempElement.next;
                    }
                    temp[newIndex].next = data[i];
                }
                element = element.next;
            }
        }
        data = temp;
    }
}
