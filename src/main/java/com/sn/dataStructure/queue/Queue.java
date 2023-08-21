package com.sn.dataStructure.queue;

public interface Queue<E> {

    void enqueue(E element);

    E dequeue();

    E peek();

    boolean isEmpty();

    void clear();

}
