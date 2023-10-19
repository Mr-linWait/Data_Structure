package com.sn.dataStructure.binaryTree;

/**
 * 二叉树节点类
 * 1.
 */
class BinaryNode <T>{
    private T data;
    private BinaryNode<T> leftTree;//左子树
    private BinaryNode<T> rightTree;//右子树

    public BinaryNode(T data,BinaryNode<T> leftTree,BinaryNode<T> rightTree){
        this.data=data;
        this.leftTree=leftTree;
        this.rightTree=rightTree;
    }

    public BinaryNode(T data){
        this(data,null,null);
    }

    public BinaryNode(){
        this(null);
    }

    public boolean hasLeftTree(){
        return leftTree!=null;
    }

    public boolean hasRightTree(){
        return rightTree!=null;
    }

    /**
     * 计算当前节点为根节点的node数量 = 左子树数量 + 右子树数量 + 自己
     * @return
     */
    public int getNumbersOfNodes(){
        int left=0;
        int right=0;
        if (leftTree!=null)
            left=leftTree.getNumbersOfNodes();
        if (rightTree!=null)
            right=rightTree.getNumbersOfNodes();
        return 1+right+left;
    }


    /**
     * 获取当前节点到叶子节点的高度
     * @return
     */
    public int getHeight(){
        return getHeight(this);
    }

    /**
     * 高度等于 左、右子树的高度最大值+自己
     * @param current 需要开始寻找的节点
     * @return
     */
    private int getHeight(BinaryNode<T> current){
        int height=0;
        if (current!=null)
            height=1+Math.max(getHeight(current.getLeftTree()),getHeight(current.getRightTree()));
        return height;
    }

    /**
     * 复制以当前节点为根的子树 不会复制data值
     * ！修改data依然会导致共享数据的问题
     * @return
     */
    public BinaryNode<T> copy(){
        BinaryNode<T> copyBinaryNode = new BinaryNode<>(data);//复制根节点
        if (leftTree!=null)
            copyBinaryNode.setLeftTree(leftTree.copy());
        if (rightTree!=null)
            copyBinaryNode.setRightTree(rightTree.copy());
        return copyBinaryNode;
    }

    /**
     * 复制以当前节点为根的子树 并且复制data值
     * ！修改data不会导致共享数据的问题
     * @return
     */
    /*public BinaryNode<T> copyChildAndData(){
        BinaryNode<T> copyBinaryNode = new BinaryNode<>(data);//复制根节点
        if (leftTree!=null)
            copyBinaryNode.setLeftTree(leftTree.copy());
        if (rightTree!=null)
            copyBinaryNode.setRightTree(rightTree.copy());
        return copyBinaryNode;
    }*/


    /**
     * 没有左右子树的就是叶子节点
     * @return
     */
    public boolean isLeaf(){
        return rightTree==null&&leftTree==null;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BinaryNode<T> getLeftTree() {
        return leftTree;
    }

    public void setLeftTree(BinaryNode<T> leftTree) {
        this.leftTree = leftTree;
    }

    public BinaryNode<T> getRightTree() {
        return rightTree;
    }

    public void setRightTree(BinaryNode<T> rightTree) {
        this.rightTree = rightTree;
    }
}
