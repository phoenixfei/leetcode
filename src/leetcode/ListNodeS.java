package leetcode;

import java.util.Stack;

public class ListNodeS {

    // https://leetcode.com/problems/intersection-of-two-linked-lists/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode l1 = headA, l2 = headB;
        while (l1 != l2) {
            l1 = l1 == null ? headB : l1.next;
            l2 = l2 == null ? headA : l2.next;
        }
        return l1;
    }

    // https://leetcode.com/problems/reverse-linked-list/
    // 递归解法
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode next = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return next;
    }

    // 迭代解法
    public ListNode reverseListIter(ListNode node) {
        ListNode reversedNode = null;
        ListNode next = null;
        while (node != null) {
            // 要分清楚 = 两侧操作所属的堆栈内存区
            next = node.next;
            node.next = reversedNode;
            reversedNode = node;
            node = next;
        }
        return reversedNode;
    }

    // https://leetcode.com/problems/merge-two-sorted-lists/
    // 递归解法
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }

    // 迭代解法
    public ListNode mergeTwoListsIter(ListNode l1, ListNode l2) {
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        ListNode cur = new ListNode(0);
        ListNode ret = cur;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                cur.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            cur = cur.next;
        }
        while (l1 != null) {
            cur.next = new ListNode(l1.val);
            l1 = l1.next;
            cur = cur.next;
        }
        while (l2 != null) {
            cur.next = new ListNode(l2.val);
            l2 = l2.next;
            cur = cur.next;
        }
        return ret.next;
    }

    // https://leetcode.com/problems/remove-duplicates-from-sorted-list/
    // 递归解法
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null)
            return head;
        // 下面这句话是递归的核心，体现迭代思想；记住，无论如何，都是head.next;
        head.next = deleteDuplicates(head.next);
        return head.val == head.next.val ? head.next : head;
    }

    // 迭代解法
    public ListNode deleteDuplicatesIter(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode ret = head;
        while (head.next != null) {
            if (head.val == head.next.val)
                head.next = head.next.next;
            else
                head = head.next;
        }
        return ret;
    }

    // https://leetcode.com/problems/remove-nth-node-from-end-of-list/
    // 引用数据类型从后面删除节点，可以利用两个指针的操作次数差来控制
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode ret = new ListNode(0);
        ListNode first = ret, second = ret;
        ret.next = head;// 注意引用数据类型变量的赋值问题；
        for (int i = 0; i <= n; i++) {
            // 注意，这里面从头到尾没有创建新的变量；
            // first指针不停的指向ret，first,second的next ListNode的位置
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        // 跳过要抛弃的节点
        second.next = second.next.next;
        return ret.next;
    }

    // https://leetcode.com/problems/swap-nodes-in-pairs/
    // 递归解法
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode l1 = head, l2 = head.next, l3 = l2.next;
        l1.next = l3;
        l2.next = l1;
        if (l3 != null)
            l1.next = swapPairs(l3);
        return l2;
    }

    // 迭代解法
    public ListNode swapPairsIter(ListNode head) {
        ListNode ret = new ListNode(0);
        ret.next = head;
        ListNode temp = ret;
        while (temp.next != null && temp.next.next != null) {
            ListNode l1 = temp.next, l2 = temp.next.next;
            ListNode next = l2.next;
            l1.next = next;
            l2.next = l1;
            temp.next = l2;
            temp = l1;
        }
        return ret.next;
    }

    // https://leetcode.com/problems/add-two-numbers-ii/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<ListNode> l1Stack = new Stack<>();
        Stack<ListNode> l2Stack = new Stack<>();
        while (l1 != null) {
            l1Stack.push(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            l2Stack.push(l2);
            l2 = l2.next;
        }
        int sum = 0;
        ListNode ret = new ListNode(-1);
        while (!l1Stack.empty() || !l2Stack.empty()) {
            if (!l1Stack.empty())
                sum += l1Stack.pop().val;
            if (!l2Stack.empty())
                sum += l2Stack.pop().val;
            ret.val = sum % 10;
            // head为进位信息
            ListNode head = new ListNode(sum / 10);
            head.next = ret;
            ret = head;
            sum /= 10;
        }
        return ret.val == 0 ? ret.next : ret;
    }
    // https://leetcode.com/problems/palindrome-linked-list/

    // https://leetcode.com/problems/split-linked-list-in-parts/
    public ListNode[] splitListToParts(ListNode root, int k) {
        int len = 0;
        ListNode cur = root;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        int mod = len % k;
        int size = len / k;
        ListNode[] ret = new ListNode[k];
        cur = root;
        for (int i = 0; cur != null && i < k; i++) {
            ret[i] = cur;
            int curSize = size + (mod-- > 0 ? 1 : 0);
            for (int j = 0; j < curSize - 1; j++) {
                cur = cur.next;
            }
            ListNode next = cur.next;
            cur.next = null;
            cur = next;
        }
        return ret;
    }

}