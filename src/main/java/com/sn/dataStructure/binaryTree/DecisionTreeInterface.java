package com.sn.dataStructure.binaryTree;

public interface DecisionTreeInterface<T> extends BinaryTreeInterface<T> {

    /**
     * 获取当前节点的数据
     * @return
     */
    public T getCurrentData();

    /**
     * 设置当前节点的数据 ！当前节点不能为null
     * @param newData
     */
    public void setCurrentDate(T newData);

    /**
     *  为当前的节点设置两个子节点的数据，如果子节点不存在就会创建该子节点
     * @param responsesForNo 左子节点的数据
     * @param responsesForYes 右子节点的数据
     */
    public void setResponses(T responsesForNo,T responsesForYes);

    /**
     * 当前节点是否包含回答
     * @return 当前节点是否包含子节点
     */
    public boolean isAnswer();

    /**
     * 设置当前节点为左子节点
     * 如果这个子节点不存在，则设置为null
     */
    public void advanceToNo();

    /**
     * 设置当前节点为右子节点
     * 如果这个子节点不存在，则设置为null
     */
    public void advanceToYes();

    /**
     * 将当前节点设置为树的跟节点
     */
    public void resetCurrentNode();

}
