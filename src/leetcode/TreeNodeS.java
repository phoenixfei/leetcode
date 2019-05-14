package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * TreeNode 树树树树树树树 https://www.jianshu.com/p/456af5480cee
 */
public class TreeNodeS {

    // 递归实现先序遍历，同理可实现中序、后序遍历问题，只要主要输出val的位置即可
    public static void recursionPreorderTravesal(TreeNode root) {
        if (root != null) {
            // System.out.println(root.val); // 先序遍历
            recursionPreorderTravesal(root.left);
            // System.out.println(root.val); // 中序遍历
            recursionPreorderTravesal(root.right);
            System.out.println(root.val); // 后序遍历
        }
    }

    // https://leetcode.com/problems/binary-tree-preorder-traversal/
    // 利用栈，迭代解法解决 先序 遍历
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> ret = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode curNode = root;
        while (curNode != null || !stack.isEmpty()) {
            while (curNode != null) {
                ret.add(curNode.val); // 先序遍历
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
            ret.add(curNode.val); // 中序遍历
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
            curNode = stack.peek(); // 后续遍历
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
    /**
     * 递归确定 二叉搜索树 1. 节点的左子树包含小于当前节点的数； 2. 节点的右子树包含大于当前节点的数； 3. 节点的左右子树自身必须也是二叉搜索树。
     */
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