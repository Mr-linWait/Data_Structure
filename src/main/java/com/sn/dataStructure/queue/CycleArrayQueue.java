package com.sn.dataStructure.queue;

public class CycleArrayQueue<E> implements Queue<E> {

    private int frontIndex;//对头下标

    private int backIndex;//对尾下标

    private static final int DEFAULT_CAPACITY = 15;//默认数组容量

    private static final int MAX_CAPACITY = Integer.MAX_VALUE - 8;//最大数组容量

    private E[] queue;

    private boolean initialized = false;//初始化标志

    public CycleArrayQueue(int initCapacity) {
        initCapacity = checkCapacity(initCapacity);
        queue = (E[]) new Object[initCapacity + 1];
        frontIndex = 0;
        backIndex = initCapacity;
        initialized = true;
    }

    public CycleArrayQueue() {
        this(DEFAULT_CAPACITY);
    }

    private int checkCapacity(int initCapacity) {
        if (initCapacity >= MAX_CAPACITY)
            initCapacity = MAX_CAPACITY - 1;
        if (initCapacity == 0)
            initCapacity = DEFAULT_CAPACITY;
        if (initCapacity < 0)
            throw new IllegalArgumentException("illegal capacity " + initCapacity);
        return initCapacity;
    }

    private void checkInitialization() {
        if (!initialized)
            throw new SecurityException(" ArrayQueue object is not initialized");
    }

    /**
     * 向队头新增一个元素
     * 先判断是否队满 frontIndex== (backIndex +2)%queue.length
     * 队满扩容（默认扩大2倍，一次位移运算）
     * 扩容后需要将 element 放置在 (backIndex+1)%queue.length
     *
     * @param element
     */
    @Override
    public void enqueue(E element) {
        checkInitialization();
        ensureCapacity();
        backIndex = (backIndex + 1) % queue.length;
        queue[backIndex] = element;
    }

    /**
     *
     */
    private void ensureCapacity() {
        int oldCapacity = queue.length;
        if (frontIndex == (backIndex + 2) % oldCapacity) {
            int newCapacity = queue.length << 1;
            newCapacity = checkCapacity(newCapacity);
            E[] newQueue = (E[]) new Object[newCapacity];
            for (int index = 0; index < oldCapacity - 1; index++) {
                newQueue[index] = queue[(frontIndex + index) % oldCapacity];
            }
            frontIndex = 0;
            backIndex = oldCapacity - 2;
            queue = newQueue;
        }
    }

    @Override
    public E dequeue() {
        E peek = peek();
        queue[frontIndex] = null;
        frontIndex = (frontIndex + 1) % queue.length;
        return peek;
    }

    @Override
    public E peek() {
        checkInitialization();
        if (isEmpty())
            throw new NullPointerException("ArrayQueue is empty ");
        return queue[frontIndex];
    }

    /**
     * 当frontIndex=(backIndex+1)%queue.length 队空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        if (frontIndex == (backIndex + 1) % queue.length)
            return true;
        return false;
    }

    @Override
    public void clear() {
        checkInitialization();
        frontIndex=0;
        backIndex=queue.length-1;
        E[] newQueue = (E[]) new Object[backIndex + 1];
        queue=null;
        queue=newQueue;
    }
}
