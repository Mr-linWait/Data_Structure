package com.sn.dataStructure.queue;

public class LinkedDeque<E> implements Deque<E> {

    private DLNode head;//对头节点

    private DLNode tail;//队尾节点

    public LinkedDeque(E head,E tail){
        DLNode headNode = new DLNode(head);
        DLNode tailNode = new DLNode(tail);
        this.head=headNode;
        this.tail=tailNode;
        headNode.next=tailNode;
        tailNode.previous=headNode;
    }

    public LinkedDeque(){
       // this(null,null);
    }


    /**
     * 尾插法
     * @param element
     */
    public void enqueue(E element) {
        DLNode newNode = new DLNode(element);
        if (isEmpty()) {
            head = newNode;
            tail =newNode;
        }else {
            tail.next=newNode;
            newNode.previous=tail;
            tail=newNode;
        }
    }

    /**
     * 头部出队
     * @return
     */
    public E dequeue() {
        E e = peek();
        DLNode next = head.next;
        if (next==null)
            tail=null;
        else
            next.previous=null;
        head=next;
        return e;
    }

    public E peek() {
        if (isEmpty())
            throw new NullPointerException("LinkedDeque Object is null");
        return head.data;
    }

    public boolean isEmpty() {
        return (head==null && tail ==null);
    }

    public void clear() {
        head=null;
        tail=null;
    }

    public void addFirst(E element) {
        DLNode newNode = new DLNode(element);
        if (isEmpty()){
            head=newNode;
            tail=newNode;
        }else {
            newNode.next=head;
            head.previous=newNode;
            head=newNode;
        }
    }

    public void addLast(E element) {
        enqueue(element);
    }

    public E removeFirst() {
        return dequeue();
    }

    public E removeLast() {
        E e = peekLast();
        DLNode previous = tail.previous;
        if (previous==null)
            head=null;
        else
            previous.next=null;
        tail=previous;
        return e;
    }

    public E peekFirst() {
        return peek();
    }

    public E peekLast() {
        if (isEmpty())
            throw new NullPointerException("LinkedDeque Object is null");
        return tail.data;
    }

    private class DLNode{

        private E data;

        private DLNode previous;

        private DLNode next;

        public DLNode(E data){
            this.data=data;
        }

        public DLNode(){
            this(null);
        }
    }
}
