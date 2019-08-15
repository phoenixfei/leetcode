package tencent;

import java.util.ArrayList;

/**
 * LinkedList
 */
public class LinkedList {

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
    // https://leetcode.com/problems/merge-two-sorted-lists/
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        ListNode cur = new ListNode(0);
        ListNode ret = cur;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val) {
                cur.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                cur.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            cur = cur.next;
        }
        while(l1 != null){
            cur.next = new ListNode(l1.val);
            l1 = l1.next;
            cur = cur.next;
        }
        while(l2 != null){
            cur.next = new ListNode(l2.val);
            l2 = l2.next;
            cur = cur.next;
        }
        return ret.next;
    }
    // https://leetcode.com/problems/delete-node-in-a-linked-list/
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
    // https://leetcode.com/problems/rotate-list/
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null) return null;
        int size = 1;
        ListNode fast = head;
        ListNode slow = head;
        while(fast.next != null){
            size ++;
            fast = fast.next;
        }
        for(int i = size - k % size; i > 1; i--){
            slow = slow.next;
        }
        // The basic idea is to link the tail of the list with the head, make it a cycle. Then count to the rotate point and cut it.        
        fast.next = head;
        head = slow.next;
        slow.next = null;
        return head;
    }
    /* https://leetcode-cn.com/problems/linked-list-cycle-ii/
     Let the distance from the first node to the the node where cycle begins be A, and let say the slow pointer travels travels A+B. The fast pointer must travel 2A+2B to catch up. The cycle size is N. Full cycle is also how much more fast pointer has traveled than slow pointer at meeting point.
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while( fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;            
            if( slow == fast ){
                ListNode start = head;
                while( start != slow ){
                    start = start.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }
}