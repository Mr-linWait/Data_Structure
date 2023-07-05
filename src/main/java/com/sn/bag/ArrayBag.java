package com.sn.bag;

public class ArrayBag<T> implements Bag<T> {

    /*负载数组*/
    private final T[] bag;

    /*包容量*/
    private int numberOfEntires;

    /*初始化标志*/
    private boolean initialized = false;

    /*默认负载容量*/
    private static final int DEFAULT_CAPACITY = 25;

    /*最大允许负载容量 int最大值*/
    private static final int MAX_CAPACITY = Integer.MAX_VALUE - 8;

    public ArrayBag(int capacity) {
        if (capacity < 0) throw new IllegalArgumentException("illeagl capacity :" + capacity);
        if (capacity == 0) capacity = DEFAULT_CAPACITY;
        if (capacity >= MAX_CAPACITY) capacity = MAX_CAPACITY;

        bag = (T[]) new Object[capacity];
        numberOfEntires = 0;
        initialized = true;//init
    }

    public ArrayBag() {
        this(DEFAULT_CAPACITY);
    }

    public boolean add(T element) {
        checkInitialized();
        boolean result;
        if (isArrayFull()) result = false;
        else {
            bag[numberOfEntires] = element;
            result = true;
        }
        return result;
    }

    public T remove() {
        return remove(numberOfEntires - 1);
    }

    public boolean remove(T element) {
        int index = findIndex(element);
        if (index <= -1) {
            return false;
        }
        return element.equals(remove(index));
    }

    private T remove(int index) {
        if (isEmpty() && index <= -1) return null;
        T t;
        if (index == numberOfEntires - 1) {
            t = bag[numberOfEntires - 1];
            bag[numberOfEntires - 1] = null;
            numberOfEntires = numberOfEntires - 1;
        } else {
            t = bag[index];
            bag[index] = bag[numberOfEntires - 1];
            bag[numberOfEntires - 1] = null;
            numberOfEntires = numberOfEntires - 1;
        }
        return t;
    }

    private int findIndex(T element) {
        checkInitialized();
        if (element == null) throw new NullPointerException("element could not null");
        int offset = 0;
        while (offset < numberOfEntires) {
            if (bag[offset].equals(element)) {
                return offset;
            }
            offset++;
        }
        return -1;
    }

    public boolean contains(T element) {
        checkInitialized();
        return findIndex(element) == -1 ? false : true;
    }

    public int getFrequencyOf(T element) {
        checkInitialized();
        if (element == null) throw new NullPointerException(" element could not null");
        int count = 0;
        for (int index = 0; index < numberOfEntires; index++) {
            if (element.equals(bag[index])) count++;
        }
        return count;
    }

    public T[] toArray() {
        checkInitialized();
        T[] result = (T[]) new Object[numberOfEntires];
        for (int index = 0; index < bag.length; index++) {
            result[index] = bag[index];
        }
        return result;
    }

    public int size() {
        return numberOfEntires;
    }

    private boolean isArrayFull() {
        return numberOfEntires >= bag.length;
    }

    private void checkInitialized() {
        if (!initialized) throw new SecurityException("ArrayBag object is not initialized");
    }

    public boolean isEmpty() {
        return numberOfEntires == 0;
    }
}
