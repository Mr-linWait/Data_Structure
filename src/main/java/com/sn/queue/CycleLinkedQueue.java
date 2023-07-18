package com.sn.queue;

public class CycleLinkedQueue<E> implements Queue<E> {

    private Node queueNode;

    private Node freeNode;

    public CycleLinkedQueue(E data){
        Node node = new Node(data);
        queueNode=node;
        freeNode=node;
        freeNode.next=node;
    }

    public CycleLinkedQueue(){
        this(null);
    }

    /**
     * 像循环链表队列中加入元素
     * 如果队满需要新增一个node
     * @param element
     */

    @Override
    public void enqueue(E element) {
        freeNode.data=element;
        if (isFullQueue())
            grow();
        else
            freeNode=freeNode.next;
    }

    private void grow() {
        Node newNode = new Node();
        newNode.next=freeNode.next;
        freeNode.next=newNode;
        freeNode=newNode;
    }

    private boolean isFullQueue() {
        return freeNode.next==queueNode;
    }

    @Override
    public E dequeue() {
        E peek = peek();
        queueNode.data=null;
        queueNode=queueNode.next;
        return peek;
    }

    @Override
    public E peek() {
        if (isEmpty())
            throw new NullPointerException("CycleLinkedQueue is empyt");
        return queueNode.data;
    }

    @Override
    public boolean isEmpty() {
        return freeNode==queueNode;
    }

    @Override
    public void clear() {
        queueNode=null;
        freeNode=null;
        Node newClearNode = new Node(null);
        queueNode=newClearNode;
        freeNode=newClearNode;
        freeNode.next=newClearNode;
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
