package com.company.cwiczenia;

import com.company.TwoWayLinkedListQueue;
import com.company.exceptions.EmptyQueueException;
import com.company.exceptions.FullQueueException;

public class FlawiusProblem {

    public int solve(int n, int k){
        try{
            TwoWayLinkedListQueue<Integer> circle = new TwoWayLinkedListQueue<>(n);
            for(int i = 1; i <= n; i++){
                circle.enqueue(i);
            }
            while(circle.size() > 1){
                for(int i = 0; i < k - 1; i++){
                    circle.enqueue(circle.dequeue());
                }
            }
            return circle.first();
        } catch (FullQueueException e) {
            e.printStackTrace();
            return -1;
        } catch (EmptyQueueException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
