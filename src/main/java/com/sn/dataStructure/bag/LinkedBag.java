package com.sn.dataStructure.bag;

public class LinkedBag<E> implements Bag<E>{

    /*链表最大长度*/
    private int MAX_CAPACITY=Integer.MAX_VALUE-8;

    /*当前链表元素个数*/
    private int size ;

    /*头节点*/
    private Node<E> head;

    /*初始化标志*/
    private boolean initialized;

    public LinkedBag(){
        this(null);
    }

    public LinkedBag(E element){
        this.head=new Node<>(element);
        this.size=0;
        this.initialized=true;
    }
    @Override
    public boolean add(E element) {
        checkInitialized();
        if (size+1>MAX_CAPACITY)
            throw new ArrayIndexOutOfBoundsException();

        return false;
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
    public void clear() {

    }

    @Override
    public boolean contains(E element) {
        return false;
    }

    @Override
    public int getFrequencyOf(E element) {
        return 0;
    }

    @Override
    public E[] toArray() {
        return (E[]) new Object[0];
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    private void checkInitialized() {
        if (!initialized) throw new SecurityException("LinkedBag object is not initialized");
    }

    static class Node<E>{
        protected E value;
        protected Node next;

        public Node(E value){
            this.value=value;
        }

        public Node(){
            this(null);
        }

    }
}
