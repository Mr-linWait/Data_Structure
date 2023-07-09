package com.sn.bag;

public interface Bag<E> {

    /*
     *
     * */
    boolean add(E element);

    E remove();

    boolean remove(E element);

    void clear();

    boolean contains(E element);

    /*
     * element 在包中出现的次数，element不能为null
     * */
    int getFrequencyOf(E element);

    E[] toArray();

    int size();

    boolean isEmpty();
}
