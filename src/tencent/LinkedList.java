package tencent;

import java.util.ArrayList;

/**
 * LinkedList
 */
public class LinkedList {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    // https://leetcode.com/problems/reverse-linked-list/
    public ListNode reverseList(ListNode head) {
        if(head == null) return null;
        ArrayList<Integer> list = new ArrayList<Integer>();
        ListNode cur = head;
        ListNode sed = head;
        while (cur != null) {
            list.add(cur.val);
            cur = cur.next;
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            sed.val = list.get(i);
            sed = sed.next;
        }
        return head;
    }
    /* iterative solution */
    public ListNode reverseListNode(ListNode node) {
        ListNode reversedNode = null;
        ListNode next = null;
        while (node != null) {
            next = node.next;
            node.next = reversedNode;
            reversedNode = node;
            node = next;
        }
        return reversedNode;
    }
    // https://leetcode.com/problems/add-two-numbers/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int val = 0;
        int flag = 0;
        ListNode ret = new ListNode(0);
        ListNode result = ret;
        while (l1!=null || l2!=null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            val = x + y + flag;
            flag = val / 10;
            ret.next = new ListNode(val % 10);
            ret = ret.next;
            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }
        if(flag != 0) ret.next = new ListNode(flag);
        return result.next;
    }
}