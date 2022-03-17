package com.tc_liuyj.class03;

/**
 * @author liuyajie
 * @date 2022/03/08/8:27 下午
 */
public class Code01_ReverseList {



    public static class Node {
        public int value;
        public Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static class DoubleNode{
        public int value;
        public DoubleNode pre;
        public DoubleNode next;

        public DoubleNode(int data) {
            value = data;
        }
    }

    /**
     * 反转链表
     * //  head
     * //   a    ->   b    ->  c  ->  null
     * //   c    ->   b    ->  a  ->  null
     *
     * 首先肯定需要遍历链表 时间复杂度最少O(N)
     *  Node pre = null;
     *  拿到a  使 a.next 指向pre， pre = a; 此时链表结构会断掉，因此需要提前保存后续链表 next = a.next;   调整顺序 { next = a.next; a.next = pre; pre = a      }
     *
     *  拿到保存e得下一个节点，为空结束。
     *
     */
    public static Node reverseLinkedList(Node head) {
        Node next = null;
        Node pre = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 反转双向链表
     * //  head
     * //   a   ->   b    ->  c
     *          <-        <-
     *
     * result  c  ->  b    ->    a
     *            <-       <-
     *
     */
    public static DoubleNode reverseDoubleList(DoubleNode head) {
        DoubleNode next = null;
        DoubleNode pre = null;
        while(head != null) {
            next = head.next;
            head.next = pre;
            head.pre = next;
            pre = head;
            head = next;
        }
        return pre;
    }
}
