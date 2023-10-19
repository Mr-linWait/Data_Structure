package com.sn.dataStructure.binaryTree;

public interface BinaryTreeInterface<T> extends Tree<T>, TreeIterator<T> {

    /**
     * 将二叉树设置为一个单节点二叉树
     * @param rootData
     */
    public void setTree(T rootData);

    /**
     * 为二叉树设置一个新的二叉树
     * @param rootData 新二叉树的根节点数据
     * @param leftTree 左子树
     * @param rightTree 右子树
     */
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree);
}

