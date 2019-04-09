package leetcode;

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

    public static void main(String[] args) {
        
    }
}