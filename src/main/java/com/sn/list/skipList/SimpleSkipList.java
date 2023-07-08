package com.sn.list.skipList;

import java.util.Random;

public class SimpleSkipList {

    private static final byte HEAD_BIT = -1;//头节点

    private static final byte TAIL_BIT = 0;//尾节点

    private static final byte DATA_BIT = 1;//数据节点

    private Node head;//头节点

    private Node tail;//尾节点

    private int size;//元素数量

    private int height;//跳表的层高

    private Random random;//随机数，决定当前元素的层级

    public SimpleSkipList() {
        size = 0;
        this.head = new Node(null, HEAD_BIT);
        this.tail = new Node(null, TAIL_BIT);
        head.right = this.tail;
        tail.left = this.head;
        random = new Random(System.currentTimeMillis());
    }

    /**
     * 查找element在跳表中对应的位置
     * 从头节点开始寻找，当下一个节点的值大于element或者到达尾节点停止寻找
     *
     * @param element
     * @return
     */
    private Node find(Integer element) {
        Node current = head;
        while (true) {
            while (current.right.bit != TAIL_BIT && current.right.value <= element)
                current = current.right;//向右查找
            if (current.down != null)//向下级开始查找
                current = current.down;
            else
                break;//底层结束
        }
        return current;
    }

    /**
     * 向跳表中新增一个元素
     * 1。通过{@link #find(Integer element)}找到element应该被插入的位置
     * 2。将新元素插入，并设置up，down，left，right节点
     *
     * @param element
     * @return
     */
    public boolean add(Integer element) {
        Node nearNode = find(element);
        Node newnode = new Node(element);
        newnode.right = nearNode.right;
        newnode.left = nearNode;
        newnode.right.left = newnode;
        nearNode.right = newnode;
        int currentLevel = 0;
        while (random.nextDouble() < 0.5d) {
            if (currentLevel >= height) {
                height++;//新增一个层级
                // 定义新的层级
                Node dumyHead = new Node(null, HEAD_BIT);
                Node dumyTail = new Node(null, TAIL_BIT);
                dumyHead.right = dumyTail;
                dumyTail.left = dumyHead;
                dumyHead.down = head;
                dumyTail.down = tail;
                head.up = dumyHead;
                tail.up = dumyTail;
                head = dumyHead;
                tail = dumyTail;
            }
            while (nearNode!=null && nearNode.up==null)//找到新node左边有上级节点的node
                nearNode=nearNode.left;
            nearNode=nearNode.up;
            Node upNode = new Node(element);
            upNode.left=nearNode;
            upNode.right=nearNode.right;
            upNode.right.left=upNode;
            upNode.left.right=upNode;
            upNode.down=newnode;
            newnode.up=upNode;
            newnode=upNode;//将新的upnode赋值给newnode，下次开始就从newNode+1层级开始，每次while都增加一个层级
            currentLevel++;
        }
        size++;
        return true;
    }

    public boolean contains(Integer element){
        Node node = find(element);
        return node.value.equals(element);
    }

    public Integer get (Integer element){
        Node node = find(element);
        return node.value.equals(element)?element:null;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public int size(){
        return size;
    }

    private static class Node {

        /*
         * 每个节点都有对应的上下左右四个节点
         * */
        private Node up, down, right, left;

        private Integer value;//存放的数据，Integer

        private byte bit;//节点类型

        public Node(Integer value, byte bit) {
            this.value = value;
            this.bit = bit;
        }

        public Node(Integer value) {
            this(value, DATA_BIT);
        }

        @Override
        public String toString() {
            return "value=" + value +
                    ", bit=" + bit;
        }
    }
}
