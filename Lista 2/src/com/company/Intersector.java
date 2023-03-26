package com.company;

import java.util.Iterator;

public class Intersector {

    public static OneWayLinkedList<Integer> intersect(
            OneWayLinkedList<Integer> list1,
            OneWayLinkedList<Integer> list2) {
        Iterator<Integer> itList1 = list1.iterator();
        Iterator<Integer> itList2 = list2.iterator();
        OneWayLinkedList<Integer> intersectList = new OneWayLinkedList<>();

        while(itList1.hasNext()){
            Integer data = itList1.next();
            if(list2.contains(data)){
                intersectList.add(data);
            }
        }
        return intersectList;
    }
}
