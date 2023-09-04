package com.sn.leetcode;


import java.util.ArrayList;
import java.util.List;

/**
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 */
public class SunOfTwoNumbers {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    private final List<Integer> sumList = new ArrayList<Integer>();

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l1Head = l1;
        ListNode l2Head = l2;
        ListNode tail = null;
        ListNode head =null;
        int carry = 0;
        while (!(carry == 0 && l1Head == null && l2Head == null)) {
            int valOne = l1Head == null ? 0 : l1Head.val;
            int valTwo = l2Head == null ? 0 : l2Head.val;
            int sum = valOne + valTwo + carry;
            carry = sum / 10;
            int remainder = sum % 10;
            if (tail == null) {
                tail = new ListNode(remainder);
                head=tail;
            }
            else {
                tail.next = new ListNode(remainder);
                tail = tail.next;
            }
            l1Head = l1Head == null ? null : l1Head.next;
            l2Head = l2Head == null ? null : l2Head.next;
        }
        return head;
    }

    public static void main(String[] args) {

    }
}

