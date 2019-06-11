package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
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

    // 树的深度 https://leetcode.com/problems/maximum-depth-of-binary-tree/
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    // https://leetcode.com/problems/minimum-depth-of-binary-tree/
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if(left == 0 || right == 0) return left + right + 1;
        return Math.min(left, right) + 1;
    }

    // 左叶子之和 https://leetcode.com/problems/sum-of-left-leaves/
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0; 
        if(isLeaf(root.left)) 
            return root.left.val + sumOfLeftLeaves(root.right);
        return sumOfLeftLeaves(root.left) + sumOfLeftLeaves(root.right);
    }

    private boolean isLeaf(TreeNode node){
        if (node == null) return false;
        return node.left == null && node.right == null;
    }

    // 最长同值路径 https://leetcode.com/problems/longest-univalue-path/
    public int longest = 0;

    public int longestUnivaluePath(TreeNode root) {
        longestRecur(root);
        return longest;
    }

    public int longestRecur(TreeNode root) {
        if(root == null) return 0;
        int left = longestRecur(root.left);
        int right = longestRecur(root.right);
        int leftPath = (root.left != null && root.left.val == root.val) ? left + 1 : 0;
        int rightPath = (root.right != null && root.right.val == root.val) ? right + 1 : 0;
        longest = Math.max(longest, leftPath + rightPath);
        return Math.max(leftPath, rightPath);
    }
    /**
     * https://leetcode.com/problems/balanced-binary-tree/ 给定一个二叉树，判断它是否是高度平衡的二叉树。
     * 本题中，一棵高度平衡二叉树定义为：一个二叉树每个节点的左右两个子树的高度差的绝对值不超过1。
     */
    private boolean result = true;

    public boolean isBalanced(TreeNode root) {
        maxBalancedDepth(root);
        return result;
    }

    private int maxBalancedDepth(TreeNode root) {
        if (root == null)
            return 0;
        int l = maxBalancedDepth(root.left);
        int r = maxBalancedDepth(root.right);
        if (Math.abs(l - r) > 1)
            result = false;
        return 1 + Math.max(l, r);
    }

    /**
     * https://leetcode.com/problems/diameter-of-binary-tree/
     * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过根结点。
     */
    private int maxLen = 0;

    public int diameterOfBinaryTree(TreeNode root) {
        maxDiameter(root);
        return maxLen;
    }

    private int maxDiameter(TreeNode root) {
        if (root == null)
            return 0;
        int l = maxDiameter(root.left);
        int r = maxDiameter(root.right);
        // 此时，l,r还没有+1，所有l+r不需要-2
        maxLen = Math.max(l + r, maxLen);
        return Math.max(l, r) + 1;
    }

    // 翻转二叉树 https://leetcode.com/problems/invert-binary-tree/
    public TreeNode invertTree(TreeNode root) {
        if (root == null)
            return null;
        TreeNode temp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(temp);
        return root;
    }

    /**
     * https://leetcode.com/problems/validate-binary-search-tree/ 递归确定 二叉搜索树 1.
     * 节点的左子树包含小于当前节点的数； 2. 节点的右子树包含大于当前节点的数； 3. 节点的左右子树自身必须也是二叉搜索树。
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

    /** BFS */
    // 层平均值 https://leetcode.com/problems/average-of-levels-in-binary-tree/
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> ret = new ArrayList<>();
        Queue<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            int cnt = nodes.size();
            double sum = 0;
            for (int i = 0; i < cnt; i++) {
                TreeNode temp = nodes.poll();
                if (temp.left != null) {
                    nodes.add(temp.left);
                }
                if (temp.right != null) {
                    nodes.add(temp.right);
                }
                sum += temp.val;
            }
            ret.add(sum / cnt);
        }
        return ret;
    }

    // 最左侧节点值 https://leetcode.com/problems/find-bottom-left-tree-value/
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            root = queue.poll();
            if(root.right!=null) queue.add(root.right);
            if(root.left!=null) queue.add(root.left);
        }
        return root.val;
    }

    // L-R之间的值 https://leetcode.com/problems/trim-a-binary-search-tree/
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if(root == null) return null;
        if(root.val < L) return trimBST(root.right, L, R);
        if(root.val > R) return trimBST(root.left, L, R);
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);
        return root;
    }

    // 第k小的数 https://leetcode.com/problems/kth-smallest-element-in-a-bst/
    public int kthSmallest(TreeNode root, int k) {
        int cnt = count(root.left);
        if(cnt == k-1) return root.val;
        else if(cnt > k-1) return kthSmallest(root.left, k);
        else return kthSmallest(root.right, k-cnt-1);
    }

    // 统计树的节点个数
    private int count(TreeNode node) {
        if(node == null) return 0;
        return 1 + count(node.left) + count(node.right);
    }

    // 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
    // https://leetcode-cn.com/problems/convert-bst-to-greater-tree/
    public int sum = 0;

    public TreeNode convertBST(TreeNode root) {
        rightSum(root);
        return root;
    }
    // 从最右侧开始对每个节点的值进行求和
    private void rightSum(TreeNode node) {
        if(node == null) return;
        rightSum(node.right);
        sum += node.val;
        node.val = sum;
        rightSum(node.left);
    }

    // 二叉查找树的最近公共祖先 https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
    public TreeNode lowestCommonAncestorOfBin(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val > p.val && root.val > q.val) return lowestCommonAncestorOfBin(root.left, p, q);
        if(root.val < p.val && root.val < q.val) return lowestCommonAncestorOfBin(root.right, p, q);
        return root;
    }

    // 二叉树的最近公共祖先 https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;
        TreeNode leftAnc = lowestCommonAncestor(root.left, p, q);
        TreeNode rightAnc = lowestCommonAncestor(root.right, p, q);
        if(leftAnc != null && rightAnc != null) return root;
        return leftAnc == null ? rightAnc : leftAnc;
    }

    // https://leetcode-cn.com/problems/house-robber-iii/
    public int rob(TreeNode root) {
        if (root == null) return 0;
        int val1 = root.val;
        if (root.left != null) val1 = val1 + rob(root.left.left) + rob(root.left.right);
        if (root.right != null) val1 = val1 + rob(root.right.left) + rob(root.right.right);
        int val2 = rob(root.left) + rob(root.right);
        return Math.max(val1, val2);
    }

}