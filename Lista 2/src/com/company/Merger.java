package com.company;

public class Merger {
    public static OneWayLinkedList<Integer> merge(
            OneWayLinkedList<Integer> list1,
            OneWayLinkedList<Integer> list2) {
        OneWayLinkedList<Integer> mergedList = new OneWayLinkedList<>();
        for(Integer element : list1){
            mergedList.add(element);
        }
        for(Integer element : list2){
            mergedList.add(element);
        }
        mergedList.sortList();
        return mergedList;
    }
}
