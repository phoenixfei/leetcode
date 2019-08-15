package tencent;

/**
 * DynamicProgramming
 */
public class DynamicProgramming {
    // https://leetcode.com/problems/climbing-stairs/
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
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
    // 注意if else的用法
    public int maxProfit(int[] prices) {
        int maxVal = 0;
        int buyPrice = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if(prices[i] < buyPrice){
                buyPrice = prices[i];
            }else if(prices[i] - buyPrice > maxVal){
                maxVal = prices[i] - buyPrice;
            }
        }
        return maxVal;
    }
    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/
    public int maxProfit2(int[] prices) {
        int maxVal = 0;
        for (int i = 1; i < prices.length; i++) {
            if(prices[i] > prices[i-1]){
                maxVal += prices[i] - prices[i-1];
            }
        }
        return maxVal;
    }
    // https://leetcode.com/problems/unique-paths-ii/
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int width = obstacleGrid[0].length;
        int[] dp = new int[width];
        dp[0] = 1;
        for (int[] row : obstacleGrid) {
            for (int j = 0; j < width; j++) {
                if (row[j] == 1)
                    dp[j] = 0;
                else if (j > 0)
                    dp[j] += dp[j - 1];
            }
        }
        return dp[width - 1];
    }
    public static void main(String[] args) {
        
    }
}