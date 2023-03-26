package com.company;

import com.sun.java.accessibility.util.TopLevelWindowListener;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Inserter {

    public static TwoWayLinkedList<String> insert(
            TwoWayLinkedList<String> list1,
            TwoWayLinkedList<String> list2,
            int beforeIndex) throws NoSuchElementException {
        if(beforeIndex >= 0 && beforeIndex < list1.size()) {
            TwoWayLinkedList<String> mergedList = new TwoWayLinkedList<>();
            int counter = 0;
            Iterator<String> iterator1 = list1.iterator();
            String data = "";
            while (iterator1.hasNext() && counter != beforeIndex) {
                data = iterator1.next();
                mergedList.add(data);
                counter++;
            }
            for (String element : list2) {
                mergedList.add(element);
            }
            while (iterator1.hasNext()) {
                mergedList.add(iterator1.next());
            }
            return mergedList;
        } else {
            throw new NoSuchElementException();
        }
    }

    public static TwoWayLinkedList<String> insert(
            TwoWayLinkedList<String> list1,
            TwoWayLinkedList<String> list2,
            String beforeElement) throws NoSuchElementException {
        TwoWayLinkedList<String> mergedList = new TwoWayLinkedList<>();
        Iterator<String> iterator1 = list1.iterator();
        String searchedValue = iterator1.next();
        while(iterator1.hasNext() && searchedValue != beforeElement){
            mergedList.add(searchedValue);
            searchedValue = iterator1.next();
        }
        if(!iterator1.hasNext()){
            throw new NoSuchElementException();
        } else {
            for (String element : list2) {
                mergedList.add(element);
            }
            while (iterator1.hasNext()) {
                mergedList.add(searchedValue);
                searchedValue = iterator1.next();
            }
            return mergedList;
        }
    }
}
