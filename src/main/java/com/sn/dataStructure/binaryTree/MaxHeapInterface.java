package com.sn.dataStructure.binaryTree;

public interface MaxHeapInterface<T extends Comparable<? super T> > {

    /**
     * 添加一个元素
     * @param newValue
     */
    public void add(T newValue);

    /**
     * 移除并返回当前堆中最大的元素,如果堆空返回null
     * @return
     */
    public T removeMax();

    /**
     * 返回堆中最大的元素，如果堆空返回null
     * @return
     */
   public T getMax();

    /**
     * 判断当前堆是否为空
     * @return
     */
   public boolean isEmpty();

    /**
     * 返回堆的元素个数
     * @return
     */
   public int size();

    /**
     * 清空堆中的元素
     */
   public void clear();
}
