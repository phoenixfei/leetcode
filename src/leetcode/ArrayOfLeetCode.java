package leetcode;

import java.util.PriorityQueue;

/**
 * ArrayProblem
 */
public class ArrayOfLeetCode {

    //https://leetcode.com/problems/max-consecutive-ones-iii/
    //利用循环的指针来确定长度，666
    public int longestOnes(int[] A, int K) {
        int i = 0, j;
        for ( j = 0; j < A.length; ++j) {
            if(A[j]==0)K--;
            if(K<0 && A[i++]==0)K++;
        }
        return j-i;
    }

    // https://leetcode.com/problems/search-a-2d-matrix-ii/
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int col = matrix.length - 1, row = matrix[0].length - 1;
        int c = 0, r = row;
        while (c <= col && r >= 0) {
            if (matrix[c][r] == target) return true;
            else if (matrix[c][r] > target) r--;
            else c++;
        }
        return false;
    }

    // https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
    public int kthSmallest(int[][] matrix, int k) {
        PriorityQueue<Trupe> pq = new PriorityQueue<>();
        int col = matrix.length, row = matrix[0].length;
        for (int i = 0; i < row; i++) {
            pq.offer(new Trupe(0, i, matrix[0][i]));
        }
        for (int i = 0; i < k - 1; i++) {
            Trupe del = pq.poll();
            if (del.x == col - 1) continue;
            pq.offer(new Trupe(del.x + 1, del.y, matrix[del.x + 1][del.y]));
        }
        return pq.peek().val;
    }

    class Trupe implements Comparable<Trupe>{

        int x, y, val;

        Trupe(int x, int y, int val){
            this.x = x; this.y = y; this.val = val;
        }

        @Override
        public int compareTo(Trupe that) {
            return this.val - that.val;
        }

    }

    public static void main(String[] args) {
        
    }
}