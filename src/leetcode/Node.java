package leetcode;

import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

public class Node {

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