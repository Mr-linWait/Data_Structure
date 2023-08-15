package com.sn.list;

import java.util.ArrayList;
import java.util.Arrays;

public class FakeList<E> implements List<E> {

    private int size;//数组长度

    private E[] elementData;//存储数组，存放线性表

    private static final int MAX_CAPACITY = Integer.MAX_VALUE - 15;

    private static final int DEFAULT_CAPACITY = 15;

    private static final Object[] DEFAULT_EMPTY_LIST = {};

    public FakeList() {
        elementData = (E[]) DEFAULT_EMPTY_LIST;
    }

    public FakeList(int capacity) {
        if (capacity > 0 && capacity < MAX_CAPACITY)
            elementData = (E[]) new Object[capacity];
        else if (capacity == 0)
            elementData = (E[]) DEFAULT_EMPTY_LIST;
        else
            throw new IllegalArgumentException("illega argument " + capacity);
    }


    @Override
    public boolean add(E element) {
        ensureCapacityInternal(size + 1);
        elementData[size++]=element;
        return true;
    }

    public boolean add(int index,E element){
        rangeCheckForAdd(index);
        ensureCapacityInternal(size+1);
        System.arraycopy(elementData,index,elementData,index+1,size-index);
        elementData[index]=element;
        return true;
    }

    private void rangeCheckForAdd(int index) {
        if (index>size&& size<0)
            throw new IndexOutOfBoundsException("Index :" +index + " Size :" +size);
    }

    /**
     * 确保数组的容量足够，不够需要扩容
     *
     * @param minCapacity 最小需要的容量
     */
    private void ensureCapacityInternal(int minCapacity) {
        if (elementData == DEFAULT_EMPTY_LIST) {
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }
        if (minCapacity - elementData.length > 0) {
            grow(minCapacity);
        }
    }

    /**
     * 扩容数组
     *
     * @param minCapacity
     */
    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity=minCapacity;
        if (newCapacity-MAX_CAPACITY>0)
            newCapacity=newCapacity>MAX_CAPACITY?Integer.MAX_VALUE:MAX_CAPACITY;
        elementData=Arrays.copyOf(elementData,newCapacity);
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public boolean remove(E element) {
        return false;
    }

    @Override
    public boolean contains(E element) {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }
}
