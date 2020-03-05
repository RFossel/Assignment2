/*
Rfossel
CS 310
Lab 2 Software decode
10/21/2019
 */

package com.lab2;

import java.util.Iterator;
import java.util.LinkedList;

public class MyQueue {
    LinkedList<java.lang.Integer> qList = new LinkedList<>();

    public MyQueue() {

    }

    @Override
    public String toString() {
        String str = new String();
        Iterator<Integer> iterator = qList.listIterator();
        while(iterator.hasNext()){
            str += iterator.next() + ",";
        }
        if(str != null){
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }

    public boolean isEmpty(){
        if(qList.getFirst() == null && qList.getLast() == null) {
            return true;
        }
        return false;
    }

    //enqueue
    public void loadValue(int val){
        qList.addLast(val);
    }

    //dequeue
    public int nextValue(){
        if(isEmpty()){
            return -1;
        }
        else{
            return qList.removeFirst();
        }
    }
    
    //Subtracts the value of the first item in the queue from everything
    public void normalize(){
        int tmp = qList.getFirst();
        for(int i = 0; i < qList.size(); i++){
            qList.set(i, qList.get(i) - tmp);
        }
    }

    //returns a user specified amount of next values ahead in the queue
    public int[] peek(int num){
        Iterator<Integer> iterator = qList.listIterator();
        int[] peekArr = new int[num];
        for(int i = 0; i < num; i++){
            if(iterator.hasNext()) {
                peekArr[i] = (int) iterator.next();
            }
        }
        return peekArr;
    }

    public int size(){
        return qList.size();
    }

}
