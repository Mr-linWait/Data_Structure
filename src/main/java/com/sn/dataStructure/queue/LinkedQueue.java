package com.sn.dataStructure.queue;

/**
 *基于链表实现的队列
 */

public class LinkedQueue<E> implements Queue<E> {

    private Node firstNode;

    private Node lastNode;

    private int size=0;



    public LinkedQueue(){
        firstNode=null;
        lastNode=null;
    }

    @Override
    public void enqueue(E element) {
        if (element==null)
            throw new IllegalArgumentException("element cloud not null");
        Node newNode = new Node(element);
        if (isEmpty())
            firstNode=newNode;
        else
            lastNode.next=newNode;
        lastNode=newNode;
        size++;
    }

    @Override
    public E dequeue() {
        E peek = peek();
        firstNode.data=null;
        firstNode=firstNode.next;
        size--;
        return peek;
    }

    @Override
    public E peek() {
        if (isEmpty())
            throw new NullPointerException("Queue is empty");
        return firstNode.data;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public void clear() {
        firstNode=null;
        lastNode=null;
        size=0;
    }

    private class Node{
        private E data;
        private Node next;

        public Node(E data){
            this.data=data;
        }

        public Node(){
            this(null);
        }
    }
}
