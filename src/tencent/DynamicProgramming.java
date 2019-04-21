package tencent;

/**
 * DynamicProgramming
 */
public class DynamicProgramming {

    public int climbStairs(int n) {
        if(n == 1 || n == 2) return n;
        int[] arr = new int[n+1];
        arr[1] = 1;
        arr[2] = 2;
        return climb(n, arr);
    }
    public int climb(int n, int[] arr) {
        if(arr[n] != 0){
            return arr[n];
        }else{
            arr[n] = climb(n-1, arr) + climb(n-2, arr);
            return arr[n];
        }
    }
}