package com.tc_liuyj.class03;

/**
 * @author liuyajie
 * @date 2022/03/09/4:48 下午
 *
 *  在链表中删除指定的数
 *  例如： 在下面的链表中删除2
 *  1 -> 2  -> 2 -> 3 -> 5 ->  7  ->  9  -> 2
 *
 *  第一次  遍历1
 *  第二次  遍历2， 因为等于2， 所以要把 1 -> 2 -> 3 -> 5 ->  7  ->  9  -> 2 连起来， pre.next = cur.next ;head = cur;
 *
 *  第三次： 遍历2  因为等于2，1 -> 3 -> 5 ->  7  ->  9  -> 2连起来，  pre.next = cur.next ;head = cur;
 *  肯定需要遍历 时间复杂度最少O(N)
 *
 *
 *
 */
public class Code02_DeleteGivenValue {

    private static class Node {
        private int value;
        private Node next;
        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    /**
     * 例如： 在下面的链表中删除2
     *  1 -> 2  -> 2 -> 3 -> 5 ->  7  ->  9  -> 2
     *
     *  第一次  遍历1
     *  第二次  遍历2， 因为等于2， 所以要把 1 -> 2 -> 3 -> 5 ->  7  ->  9  -> 2 连起来， pre.next = cur.next ;head = cur;
     *
     *  第三次： 遍历2  因为等于2， 1 -> 3 -> 5 ->  7  ->  9  -> 2连起来，  pre.next = cur.next ;head = cur;
     *  。
     *  。
     *
     */
    public static Node removeValue(Node head, int num) {
        // 先解决刚是开始要删除的节点
        while (head != null) {
            if (head.value != num) {
                break;
            }
            head = head.next;
        }
        // 1 ) head == null
        // 2 ) head != null
        Node pre = head;
        Node cur = head;
        while (cur != null) {
            if (cur.value == num) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}
