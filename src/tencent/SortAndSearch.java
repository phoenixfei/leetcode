package tencent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * SortAndSearch
 */

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
    // https://leetcode.com/problems/kth-largest-element-in-an-array/
    public int findKthLargest(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            // 冒泡排序的内部循环，是从前面遍历整个数组，到后面少i个元素
            for (int j = 0; j < nums.length-i-1; j++) {
                if(nums[j] > nums[j+1]) {
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
            }
        }
        return nums[nums.length-k];
    }
    // https://leetcode.com/problems/kth-smallest-element-in-a-bst/comments/
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> valList = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curNode = root;
        while (curNode != null || !stack.isEmpty()) {
            while(curNode != null){
                valList.add(curNode.val);
                stack.push(curNode);
                curNode = curNode.left;
            }
            curNode = stack.pop().right;
        }
        Collections.sort(valList);
        return valList.get(k-1);
    }
    // https://leetcode.com/problems/maximum-depth-of-binary-tree/
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        Stack<TreeNode> stack = new Stack<>();
        Stack<Integer> maxDepth = new Stack<>();
        TreeNode curNode = root;
        stack.push(root);
        maxDepth.push(1);
        int max = 0, depth = 0;
        while (!stack.isEmpty()) {
            curNode = stack.pop();
            depth = maxDepth.pop();
            if(curNode.left == null && curNode.right == null) {
                max = Math.max(depth, max);
            }
            if(curNode.left != null){
                stack.push(curNode.left);
                maxDepth.push(depth+1);
            }
            if(curNode.right != null){
                stack.push(curNode.right);
                maxDepth.push(depth+1);
            }
        }
        return max;
    }
    public int maxDepthByRecursion(TreeNode root) {
        if(root == null) return 0;
        return 1 + Math.max(maxDepthByRecursion(root.left), maxDepthByRecursion(root.right));
    }
}