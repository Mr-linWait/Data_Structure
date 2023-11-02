package com.sn.leetcode;


import java.util.concurrent.ArrayBlockingQueue;

/**
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 * <p>
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 * <p>
 * 叶节点 是指没有子节点的节点。
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,3]
 * 输出：25
 * 解释：
 * 从根到叶子节点路径 1->2 代表数字 12
 * 从根到叶子节点路径 1->3 代表数字 13
 * 因此，数字总和 = 12 + 13 = 25
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [4,9,0,5,1]
 * 输出：1026
 * 解释：
 * 从根到叶子节点路径 4->9->5 代表数字 495
 * 从根到叶子节点路径 4->9->1 代表数字 491
 * 从根到叶子节点路径 4->0 代表数字 40
 * 因此，数字总和 = 495 + 491 + 40 = 1026
 */
public class AllNodeSum {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 层序遍历
     *
     * @param root
     * @return
     */
    public int sumNumbers(TreeNode root) throws InterruptedException {
        ArrayBlockingQueue<TreeNode> treeNodes = new ArrayBlockingQueue<>(1000);
        TreeNode current = root;
        int sum = 0;
        treeNodes.put(current);

        while (!treeNodes.isEmpty()) {
            TreeNode poll = treeNodes.poll();
            if (poll.left != null) {
                poll.left.val = poll.val * 10 + poll.left.val;
                treeNodes.put(poll.left);
            }
            if (poll.right != null) {
                poll.right.val = poll.val * 10 + poll.right.val;
                treeNodes.put(poll.right);
            }
            if (poll.right == null && poll.left == null)
                sum = sum + (poll.val);
        }
        return sum;
    }
}
