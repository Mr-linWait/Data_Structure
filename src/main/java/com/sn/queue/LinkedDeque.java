package com.sn.queue;

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
        this(null,null);
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
