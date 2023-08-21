package com.sn.dataStructure.list;

public interface List<E> {

    boolean add(E element);

    E remove();

    boolean remove(E element);

    boolean contains(E element);

    boolean isEmpty();

    int size();


}
