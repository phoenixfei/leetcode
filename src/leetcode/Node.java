package leetcode;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class Node {

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
        if(head == null || head.next == null) return head;
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
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }else{
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
    // 迭代解法
    public ListNode mergeTwoListsIter(ListNode l1, ListNode l2) {
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
    // https://leetcode.com/problems/remove-duplicates-from-sorted-list/
    // 递归解法
    public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null) return head;
        // 下面这句话是递归的核心，体现迭代思想；记住，无论如何，都是head.next;
        head.next = deleteDuplicates(head.next);
        return head.val == head.next.val ? head.next: head;
    }
    // 迭代解法
    public ListNode deleteDuplicatesIter(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode ret = head;
        while (head.next != null) {
            if(head.val == head.next.val) head.next = head.next.next;
            else head = head.next;
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
        if(head == null || head.next == null) return head;
        ListNode l1 = head, l2 = head.next, l3 = l2.next;
        l1.next = l3;
        l2.next = l1;
        if(l3 != null) l1.next = swapPairs(l3);
        return l2;
    }
    // 迭代解法
    public ListNode swapPairsIter(ListNode head) {
        ListNode ret = new ListNode(0);
        ret.next = head;
        ListNode temp = ret;
        while(temp.next != null && temp.next.next != null){
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
            if (!l1Stack.empty()) sum += l1Stack.pop().val;
            if(!l2Stack.empty()) sum += l2Stack.pop().val;
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
            len ++;
            cur = cur.next;
        }
        int mod = len % k;
        int size = len / k;
        ListNode[] ret = new ListNode[k];
        cur = root;
        for (int i = 0; cur != null && i < k; i++) {
            ret[i] = cur;
            int curSize = size + (mod-- > 0 ? 1 : 0);
            for (int j = 0; j < curSize-1; j++) {
                cur = cur.next;
            }
            ListNode next = cur.next;
            cur.next = null;
            cur = next;
        }
        return ret;
    }

    public ListNode removeElements(ListNode head, int val) {
        ListNode ret = new ListNode(0);
        ListNode first = head, second = ret;
        ret.next = head;
        while (first != null) {
            if (first.val == val) {
                second.next = second.next.next;
            } else {
                second = second.next;
            }
            first = first.next;
        }
        return ret.next;
    }
    
    //*************************** */
    // 递归实现先序遍历，同理可实现中序、后序遍历问题，只要主要输出val的位置即可
    // https://www.jianshu.com/p/456af5480cee
    /**
     * private void recursionPreorderTravesal(TreeNode root) { if (root != null) {
     * recursionPreorderTravesal(root.left); recursionPreorderTravesal(root.right);
     * System.out.println(root.val); } }
     */

    // https://leetcode.com/problems/binary-tree-preorder-traversal/
    // 利用栈，实现迭代解法解决 先序 遍历
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curNode = root;
        while (curNode != null || !stack.isEmpty()) {
            while (curNode != null) {
                ret.add(curNode.val);
                stack.add(curNode);
                curNode = curNode.left;
            }
            curNode = stack.pop().right;
        }
        return ret;
    }

    // https://leetcode.com/problems/binary-tree-inorder-traversal/
    // 利用栈，实现迭代解法解决 中序 遍历
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();// 后进先出
        TreeNode curNode = root;
        while (curNode != null || !stack.empty()) {
            while (curNode != null) {
                stack.add(curNode);
                curNode = curNode.left;
            }
            curNode = stack.pop();
            ret.add(curNode.val);
            curNode = curNode.right;
        }
        return ret;
    }

    // https://leetcode.com/problems/binary-tree-postorder-traversal/
    // 利用栈，实现迭代解法解决 后序 遍历
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curNode = root;
        TreeNode lastVisit = root;
        while (curNode != null || !stack.empty()) {
            while (curNode != null) {
                stack.add(curNode);
                curNode = curNode.left;
            }
            curNode = stack.peek();
            if (curNode.right == null || curNode == lastVisit) {
                ret.add(curNode.val);
                stack.pop();
                lastVisit = curNode;
                curNode = null;
            } else {
                curNode = curNode.right;
            }
        }
        return ret;
    }

    // https://leetcode.com/problems/validate-binary-search-tree/
    // 递归确定二叉树
    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        return isValidBSTHelper(root, null, null);
    }
    private boolean isValidBSTHelper(TreeNode node, Integer left_limit, Integer right_limit) {
        if (left_limit != null && left_limit >= node.val)
            return false;
        if (right_limit != null && right_limit <= node.val)
            return false;
        boolean leftIsValid = node.left == null ? true : isValidBSTHelper(node.left, left_limit, node.val);
        if (leftIsValid) {
            return node.right == null ? true : isValidBSTHelper(node.right, node.val, right_limit);
        } else {
            return false;
        }
    }

}