package com.sn.dataStructure.binaryTree;

import com.sn.dataStructure.queue.LinkedQueue;
import com.sn.dataStructure.queue.Queue;

import java.util.*;

/**
 * 二叉树
 *
 * @param <T>
 */
public class BinaryTree<T> implements BinaryTreeInterface<T> {

    private BinaryNode<T> root;

    public BinaryTree() {
    }

    public BinaryTree(T rootData) {
        root = new BinaryNode<>(rootData);
    }

    public BinaryTree(T rootData, BinaryTree<T> leftTree, BinaryTree<T> rightTree) {
        privateSetTree(rootData, leftTree, rightTree);
    }

    /**
     * 1.将rootData设置为根节点
     * 2.如果 @param leftTree不为空 则将根节点的 leftTree 的设置为 @param leftTree.root
     * 3.如果 @param rightTree 不为空 则将根节点的 rightTree 的设置为 @param rightTree.root
     * 如果 @param leftTree 和 @param rightTree 为同一棵树 则将 @param rightTree 复制到根节点的 rightTree
     * 4.如果 @param rightTree 存在并且与当前调用privateSetTree方法的树不是同一个则将 @param rightTree 的root 设置为null
     * 5.如果 @param leftTree 存在并且与当前调用privateSetTree方法的树不是同一个则将 @param leftTree 的root 设置为null
     *
     * @param rootData  根节点数据
     * @param leftTree  左子树
     * @param rightTree 右子树
     */
    private void privateSetTree(T rootData, BinaryTree leftTree, BinaryTree rightTree) {
        root = new BinaryNode(rootData);
        if (leftTree != null && !leftTree.isEmpty())
            root.setLeftTree(leftTree.root);
        if (rightTree != null && !rightTree.isEmpty()) {
            if (leftTree != rightTree)
                root.setRightTree(rightTree.root);
            else
                root.setRightTree(rightTree.root.copy());
        }
        if (leftTree != null && leftTree != this)
            leftTree.clear();
        if (rightTree != null && rightTree != this)
            rightTree.clear();
    }

    @Override
    public void setTree(T rootData) {
        root = new BinaryNode<>(rootData);
    }

    @Override
    public void setTree(T rootData, BinaryTreeInterface<T> leftTree, BinaryTreeInterface<T> rightTree) {
        privateSetTree(rootData, (BinaryTree) leftTree, (BinaryTree) rightTree);
    }

    @Override
    public T getRoot() {
        return getRootData();
    }

    @Override
    public int height() {
        return root.getHeight();
    }

    @Override
    public int getNumberOfNodes() {
        return root.getNumbersOfNodes();
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void clear() {
        root = null;
    }

    public T getRootData() {
        if (isEmpty())
            throw new NullPointerException();
        else
            return root.getData();
    }

    protected void setRootData(T rootData) {
        root.setData(rootData);
    }

    protected void setRootNode(BinaryNode<T> rootNode) {
        root = rootNode;
    }

    protected BinaryNode<T> getRootNode() {
        return root;
    }

    @Override
    public Iterator<T> getPreorderIterator() {
        return null;
    }

    @Override
    public Iterator<T> getPostorderIterator() {
        return null;
    }

    @Override
    public Iterator<T> getInorderIterator() {
        return null;
    }

    @Override
    public Iterator<T> getLevelorderIterator() {
        return null;
    }

    /**
     * 中序遍历迭代器
     * 1 从根节点开始将左节点入栈
     * 2 currentNode 为空执行出栈，将当前节点右节点赋值currentNode
     * 3 重复
     */
    private class InorderIterator implements Iterator<T> {
        private Stack<BinaryNode<T>> nodeStack;
        private BinaryNode<T> currentNode;

        protected InorderIterator() {
            nodeStack = new Stack<>();
            currentNode = root;
        }

        @Override
        public boolean hasNext() {
            return !nodeStack.isEmpty() || currentNode != null;
        }

        @Override
        public T next() {
            BinaryNode<T> nextNode;
            while (currentNode != null) {
                nodeStack.push(currentNode);
                currentNode = currentNode.getLeftTree();
            }
            if (!nodeStack.isEmpty()) {
                nextNode = nodeStack.pop();
                if (nextNode != null)
                    currentNode = nextNode.getRightTree();
            } else
                throw new NoSuchElementException();
            return nextNode.getData();
        }
    }

    /**
     * 前序遍历迭代器
     * 1 当前节点入栈
     * 2 栈不空出栈，同时将右子树入栈
     * 3 下一个节点设置为出栈对象的左子树
     */
    private class PreOrderIterator implements Iterator<T> {
        private Stack<BinaryNode<T>> nodeStack;
        private BinaryNode<T> currentNode;

        protected PreOrderIterator() {
            nodeStack = new Stack<>();
            currentNode = root;
        }

        @Override
        public boolean hasNext() {
            return !nodeStack.isEmpty() || currentNode != null;
        }

        @Override
        public T next() {
            BinaryNode<T> nextNode;
            if (currentNode != null)
                nodeStack.push(currentNode);
            if (!nodeStack.isEmpty()) {
                nextNode = nodeStack.pop();
                if (currentNode.getRightTree() != null)
                    nodeStack.push(currentNode.getRightTree());
                currentNode = nextNode.getLeftTree();
            } else
                throw new NoSuchElementException();
            return nextNode.getData();
        }
    }

    /**
     * 后续遍历迭代器
     * 1.根节点入栈 不断获取左节点入栈
     * 2.获取栈顶元素 判断是否有右子树
     * 3.有右子树重复 1 2 3 步骤
     * 4.没有右子树则将出栈
     */
    private class PostorderIterator implements Iterator<T> {
        private Stack<BinaryNode<T>> nodeStack;
        private BinaryNode<T> currentNode;
        private BinaryNode<T> preNode;

        protected PostorderIterator() {
            nodeStack = new Stack<>();
            currentNode = root;
        }

        @Override
        public boolean hasNext() {
            return !nodeStack.isEmpty() || currentNode != null;
        }

        @Override
        public T next() {
            BinaryNode<T> nextNode;
            while (true) {
                if (currentNode != null) {
                    nodeStack.push(currentNode);
                    currentNode = currentNode.getLeftTree();
                } else {
                    preNode = nodeStack.peek();
                    if (preNode.getRightTree() != null)
                        currentNode = preNode.getRightTree();
                    else
                        break;
                }
            }
            if (!nodeStack.isEmpty()) {
                nextNode = nodeStack.pop();
                currentNode = null;
            } else
                throw new NoSuchElementException();
            return nextNode.getData();
        }
    }

    /**
     * 层序遍历迭代器
     * 1.根节点入队，出队，分别获取左、右子树入队
     * 2.重复1
     */
    private class LevelorderIterator implements Iterator<T> {
        private Queue<BinaryNode<T>> nodeQueue;
        private BinaryNode<T> currentNode;

        protected LevelorderIterator() {
            nodeQueue = new LinkedQueue<>();
            currentNode = root;
        }

        @Override
        public boolean hasNext() {
            return !nodeQueue.isEmpty() || currentNode != null;
        }

        @Override
        public T next() {
            BinaryNode<T> nextNode;
            if (currentNode != null) {
                nodeQueue.enqueue(currentNode);
            }
            currentNode=null;
            if (!nodeQueue.isEmpty()) {
                nextNode = nodeQueue.dequeue();
                if (nextNode.getLeftTree() != null)
                    nodeQueue.enqueue(nextNode.getLeftTree());
                if (nextNode.getRightTree() != null)
                    nodeQueue.enqueue(nextNode.getRightTree());
            }else
                throw new NoSuchElementException();
            return nextNode.getData();
        }
    }

}
