package com.sn.dataStructure.binaryTree;

import java.util.Iterator;

public interface TreeIterator<T>  {

    /**
     * 前序遍历迭代器
     * @return
     */
    public Iterator<T> getPreorderIterator();

    /**
     * 后续遍历迭代器
     * @return
     */
    public Iterator<T> getPostorderIterator();

    /**
     * 中序遍历迭代器
     * @return
     */
    public Iterator<T> getInorderIterator();


    /**
     * 层序遍历迭代器
     * @return
     */
    public Iterator<T> getLevelorderIterator();

}
