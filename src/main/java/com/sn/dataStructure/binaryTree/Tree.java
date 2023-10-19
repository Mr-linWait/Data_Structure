package com.sn.dataStructure.binaryTree;

/**
 * 树的顶级接口
 * 1.一般树
 * 2.二叉树
 * 1.满二叉树 ：所有节点都在同一层级且每个节点都拥有2个子节点
 * 2.完全二叉树：除最后一层外都拥有2个节点，最后一个层的节点是从左到右；
 * 3.高度平衡树：每个节点的子树高数相差不大于1(完全二叉树、满二叉树都是平衡树)
 * 4.完全平衡树：每个节点的子树高度都一样 满二叉树
 * 搜索方式
 * 1.广度优先 层序遍历
 * 2.深度优先 前序遍历
 * 3.前序，后续，中序遍历 是指便利根节点的时机；
 */
public interface Tree<T> {
    /**
     * 获取根节点
     * @return
     */
    T getRoot();

    /**
     * 获取树的高度
     * @return
     */
    int height();

    /**
     * 获取树的总节点数
     * @return
     */
    int getNumberOfNodes();

    boolean isEmpty();

    void clear();
}
