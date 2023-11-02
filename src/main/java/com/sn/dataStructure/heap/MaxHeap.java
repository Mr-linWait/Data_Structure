package com.sn.dataStructure.heap;


public class MaxHeap<T extends Comparable<? super T>> implements MaxHeapInterface<T> {

    private T[] heap;//存放元素的数组，从1开始

    private int lastIndex;//最后一个元素的位置

    private boolean initialized = false;

    private static final int DEFAULT_CAPACITY = 25;

    private static final int MAX_CAPACITY = 1000;

    public MaxHeap(int capacity) {
        if (capacity > MAX_CAPACITY)
            throw new IllegalArgumentException();
        if (capacity < 0)
            capacity = DEFAULT_CAPACITY;
        heap = (T[]) new Comparable[capacity + 1];
        lastIndex = 0;
        initialized = true;
    }

    public MaxHeap() {
        this(DEFAULT_CAPACITY);
    }


    @Override
    public void add(T newValue) {
        if (lastIndex==MAX_CAPACITY)
            return;
        if (isEmpty())
            heap[1]=newValue;
        else{
            int parentIndex=(lastIndex+1)/2;
            int childIndex=lastIndex+1;
            while (true)
            if (newValue.compareTo(heap[parentIndex])>0){
                heap[childIndex]=heap[parentIndex];
                heap[parentIndex]=null;
                childIndex=parentIndex;
                parentIndex=parentIndex/2;
            }else {
                heap[childIndex]=newValue;
                break;
            }
        }
        lastIndex++;
        ensureCapacity();
    }

    private void ensureCapacity() {
        if (lastIndex==heap.length-1)

    }

    @Override
    public T removeMax() {
        return null;
    }

    @Override
    public T getMax() {
        if (!isEmpty())
            return heap[1];
        return null;
    }

    @Override
    public boolean isEmpty() {
        return lastIndex < 1;
    }

    @Override
    public int size() {
        return lastIndex;
    }

    @Override
    public void clear() {
        while (lastIndex>=1)
            heap[lastIndex]=null;
        lastIndex--;
    }
}
