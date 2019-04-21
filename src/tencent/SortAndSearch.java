package tencent;

/**
 * SortAndSearch
 */
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

public class SortAndSearch {
    // https://leetcode.com/problems/sort-list/
    // 冒泡排序超出了时间限制
    public ListNode sortListByBubble(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode first = head;
        ListNode second = head;
        ListNode finished = null;
        while(first.next != finished){
            second = first.next;
            while(second != first){
                if(first.val < second.val){
                    first.val = first.val ^ second.val;
                    second.val = first.val ^ second.val;
                    first.val = first.val ^ second.val;
                }
                if(second.next != finished){
                    second = second.next;
                    first = first.next;
                }
            }
            finished = second;
            first = second = head;
        }
        return head;
    }
    // 归并排序
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        // step 1. cut the list to two halves
        ListNode slow = head, fast = head, pre = head;
        while(fast != null && fast.next != null){
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        pre.next = null;
        // step 2. sort each half
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(slow);
        // step 3. merge l1 and l2
        ListNode ret = new ListNode(0);
        ListNode cur = ret;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                cur.next = l1;
                l1 = l1.next;
            }else{
                cur.next= l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if(l1 != null){
            cur.next = l1;
        }
        if(l2 != null){
            cur.next = l2;
        }
        return ret.next;
    }

}