package com.sn.bag;

public interface Bag<T> {

    /*
     *
     * */
    boolean add(T element);

    boolean remove(T element);

    boolean contains(T element);

    /*
     * element 在包中出现的次数，element不能为null
     * */
    int getFrequencyOf(T element);

    T[] toArray();

    int size();

    boolean isEmpty();
}
