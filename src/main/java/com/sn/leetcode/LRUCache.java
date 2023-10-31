package com.kingdee.email;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 *请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 *
 *
 *
 * 示例：
 *
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 */
public class LRUCache {

    private final int MAX_CAPACITY;//最大负载量

    private final Map<Integer,LRUNode> nodeHashMap;//索引散列，存放元素节点

    private LRUNode head;//存放元素的双向链表，表头元素

    private LRUNode tail;//表尾元素

    public LRUCache(int capacity) {
        if (capacity<=0||capacity>3000)
            throw new IllegalArgumentException();
        nodeHashMap=new HashMap(capacity);
        head=new LRUNode();
        tail=new LRUNode();
        head.next=tail;
        tail.previous=head;
        MAX_CAPACITY=capacity;
    }

    /**
     * 从nodeHashMap获取元素节点，如果不存在返回-1，如果存在将节点移到表头
     * @param key
     * @return
     */
    public int get(int key) {
        if (!nodeHashMap.containsKey(key))
            return -1;
        LRUNode lruNode = nodeHashMap.get(key);
        //移至表头：断开lrunode前后节点引用，插入表头
        moveToHead(lruNode);
        return lruNode.value;
    }

    /**
     * 当 key 存在的时候，直接替换value，并且移到表头
     * 当 key 不存在的时候 1 如果元素已经达到最大容量，剔除表尾元素，将新元素加入表头
     *                    2 直接插入表头
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        if (nodeHashMap.containsKey(key)){
            LRUNode lruNode = nodeHashMap.get(key);
            lruNode.value=value;
            //移动至表头
            moveToHead(lruNode);
        }else {
            LRUNode lruNode = new LRUNode(key, value);
            if (nodeHashMap.size()>=MAX_CAPACITY)
                removeOlderLRUNode();
            head.next.previous=lruNode;
            lruNode.next=head.next;
            lruNode.previous=head;
            head.next=lruNode;
            nodeHashMap.put(key,lruNode);
        }
    }

    private void moveToHead(LRUNode lruNode) {
        lruNode.previous.next=lruNode.next;
        lruNode.next.previous=lruNode.previous;
        head.next.previous=lruNode;
        lruNode.next=head.next;
        lruNode.previous=head;
        head.next=lruNode;
    }

    private void removeOlderLRUNode() {
        LRUNode previous = tail.previous;
        tail.previous.previous.next=tail;
        tail.previous=tail.previous.previous;
        nodeHashMap.remove(previous.key);
    }

    private class LRUNode{
        protected int key;
        protected int value ;
        protected LRUNode previous;
        protected LRUNode next;

        public LRUNode(int key,int value){
            this.key=key;
            this.value=value;
        }
        public LRUNode(){

        }
    }
}
