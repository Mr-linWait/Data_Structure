package com.sn.bag;

import java.util.Arrays;
/**
 * 基于动态数组实现的包
 * 1.因为包不维护项的次序，所以删除一项不需要将后续的所有数组项前移一个位置，用数组中最后一项来替换想删除的项，
 *   然后将最后一项替换为 nu11。。
 * 2.调整数组大小，使得它看上去能改变大小。为此，分配一个新数组长度增加原有数组的2倍，从原始数组中将项复制到数组中
 */
public class ArrayBag<T> implements Bag<T> {

    /*负载数组 transient 禁止序列化*/
    private transient T[] bag;

    /*包容量*/
    private int numberOfEntires;

    /*初始化标志*/
    private boolean initialized = false;

    /*默认负载容量*/
    private static final int DEFAULT_CAPACITY = 25;

    /*最大允许负载容量 int最大值*/
    private static final int MAX_CAPACITY = Integer.MAX_VALUE - 8;

    public ArrayBag(int capacity) {
        capacity=checkCapacity(capacity);
        bag = (T[]) new Object[capacity];
        numberOfEntires = 0;
        initialized = true;//init
    }

    public ArrayBag() {
        this(DEFAULT_CAPACITY);
    }

    private int checkCapacity(int capacity){
        if (capacity < 0) throw new IllegalArgumentException("illeagl capacity :" + capacity);
        if (capacity == 0) capacity = DEFAULT_CAPACITY;
        if (capacity >= MAX_CAPACITY) capacity = MAX_CAPACITY;
        return capacity;
    }

    private void grow(){
        int oldCapacity=bag.length;
        int newCapacity =checkCapacity(oldCapacity+oldCapacity>>1);
        bag = Arrays.copyOf(bag, newCapacity);
    }

    public boolean add(T element) {
        checkInitialized();
        if (isArrayFull()) {
            grow();
        }
        bag[numberOfEntires] = element;
        return true;
    }

    public T remove() {
        checkInitialized();
        return removeIndex(numberOfEntires - 1);
    }

    public boolean remove(T element) {
        checkInitialized();
        int index = findIndex(element);
        if (index <= -1) {
            return false;
        }
        return element.equals(removeIndex(index));
    }

    private T removeIndex(int index) {
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

    public void clear(){
        while (!isEmpty()){
            remove();
        }
        numberOfEntires=0;
    }


























}
