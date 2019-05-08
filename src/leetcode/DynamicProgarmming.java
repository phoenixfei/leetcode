package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * DynamicProgarmming
 * Those who cannot remember the past are condemned to repeat it.
 * https://blog.csdn.net/u013309870/article/details/75193592
 * 动态规划的本质是去除递归的冗余计算，来大幅提高自己的性能，降低复杂度。
 * 递归是自顶向下的，动归是自底向上的，然后，把缓存冗余的计算结果的计算过程，变成自底向上的过程，就变成符合动归格式的代码了。
 * 1.自顶向下的备忘录法；2.自底向上的动态规划
 * 下述以斐波拉契数列为例，给出递归、动态规划的解法。
 *  Fibonacci (n) = 0;   n = 0

    Fibonacci (n) = 1;   n = 1, 2 

    Fibonacci (n) = Fibonacci(n-1) + Fibonacci(n-2)
 */
public class DynamicProgarmming {
    // 递归版本
    // 优缺点：可读性最好，但是很多节点被重复执行，空间开销太大
    public static int fib(int num) {
        if(num <= 1)return num;
        else{
            return fib(num - 1) + fib(num -2);
        }
    }
    // 利用动态规划的两种方法解决斐波拉契数列问题
    // 方法1：自顶向下记录已求解的节点信息
    public static int fibDP(int num, int[] nums) {
        //注意if判断的顺序
        if (nums[num] != -1) return nums[num];
        if(num == 0) nums[num] = 0;
        else if(num == 1) nums[num] = 1;       
        else nums[num] = fibDP(num-1, nums) + fibDP(num-2, nums);
        return nums[num];
    }
    // 方法2：自底向上求解，并记录节点信息
    public static int fibDP(int num) {
        if(num <= 0) return num;
        int[] nums = new int[num+1];
        Arrays.fill(nums, -1);
        nums[0] = 0;
        nums[1] = 1;
        for (int i = 2; i <= num; i++) {
            nums[i] = nums[i-1] + nums[i-2];
        }
        return nums[num];
    }
    // 方法2利用一个长度为num的数组来存储数据，可以降低其空间复杂度
    // 仅利用三个变量记录所需的值，-2,-1,0
    public static int fibDP2(int num) {
        if(num <= 0) return num;
        int ret = 1; // num_0
        int num_1 = 1;
        int num_2 = 0;
        for (int i = 2; i <= num; i++) {
            ret = num_1 + num_2;
            num_2 = num_1;
            num_1 = ret;
        }
        return ret;
    }
    // 斐波拉契数列的动态规划总结：一般来说，使用自顶向下的方法使用了递归，产生了额外空间开销，使用自底向上的方法较好。
/*     public static void main(String[] args) {
        int num = 4;
        // 递归版本
        System.out.println(fib(num));
        // 方法1：自顶向下记录已求解的节点信息
        int[] nums = new int[num + 1];
        Arrays.fill(nums, -1);
        System.out.println(fibDP(num, nums));
        // 方法2：自底向上求解，并记录节点信息
        System.out.println(fibDP(num));
        System.out.println(fibDP2(num));
    } */

    /**
     * 钢条切割问题：给定一段长度为n英寸的钢条和一个价格表pi(i=1,2,3...,n)。求切割钢条方案，使得销售利益最大。
     */
    // 递归版本
    public static int cut(int[] p, int n) {
        if(n == 0) return 0;
        int ret = 0; // 记录某种切割方法的最大利益值
        for (int i = 1; i <= n; i++) {
            ret = Math.max(ret, cut(p, n-i)+p[i-1]);
        }
        return ret;
    }
    // 自顶向下+递归版本（动态规划）
    public static int cut(int[] p, int n, int[] q) {
        if(n == 0) return 0;
        if(q [n] != -1) return q[n];
        int ret = 0;
        for (int i = 1; i <= n; i++) {
            ret = Math.max(ret, cut(p, n-i, q)+p[i-1]);
            q[n] = ret; // for循环外面、里面写，效果一致
        }
        return q[n];
    }
    // 自底向上（动态规划）
    public static int cutDP(int[] p, int n) {
        int[] q = new int[n+1];
        Arrays.fill(q, -1);
        int ret = 0;
        for (int i = 0; i <= n; i++) {
            ret = 0;
            for (int j = 1; j <= i; j++){
                ret = Math.max(ret, p[j-1]+q[i-j]);
            }
            q[i] = ret;
        }
        System.out.println(Arrays.toString(q));
        return q[n];
    }
/*     public static void main(String[] args) {
        int[] p = {1,5,8,9,10,17,17,20,24,30};
        int n = 5;
        // 动态规划
        System.out.println(cut(p, n));
        // 自顶向下
        int[] q = new int[n+1];
        Arrays.fill(q, -1);
        System.out.println(cut(p, n, q));
        // 自底向上
        System.out.println(cutDP(p, n));
    } */
    /* 斐波拉切数列与切割钢条问题总结：
        1. 利用自顶向下(内部包含一个递归)，该方法比自底向上少一个循环。原因即体现在递归上。
        2. 动态规划的两种解法（自顶向下与自底向上），都利用一个数组存储了已经计算出来的节点信息，即比递归解法少了空间消耗。
        3. 如果需要保存的节点个数确定，对比斐波拉契数列自底向上问题优化解法，可以进一步降低空间消耗。
     */


    // 动态规划的经典问题
    /* 
    1. 线性模型
        线性模型的是动态规划中最常用的模型，上文讲到的钢条切割问题就是经典的线性模型，这里的线性指的是状态的排布是呈线性的。
        
        经典案例：
        在一个夜黑风高的晚上，有n（n <= 50）个小朋友在桥的这边，现在他们需要过桥，但是由于桥很窄，每次只允许不大于两人通过，他们只有一个手电筒，所以每次过桥的两个人需要把手电筒带回来，i号小朋友过桥的时间为T[i]，两个人过桥的总时间为二者中时间长者。问所有小朋友过桥的总时间最短是多少。
    2. 区间模型
        区间模型的状态表示一般为d[i][j]，表示区间[i, j]上的最优解，然后通过状态转移计算出[i+1, j]或者[i, j+1]上的最优解，逐步扩大区间的范围，最终求得[1, len]的最优解。
        经典案例：
          回文串
        例1：给定一个长度为n（n <= 1000）的字符串A，求插入最少多少个字符使得它变成一个回文串。 
          解析：
        例2：给定一个字符串 s，找到 s 中最长的回文子串。
    3. 背包问题
        
     */

    // test https://github.com/CyC2018/CS-Notes/blob/master/notes/Leetcode%20%E9%A2%98%E8%A7%A3%20-%20%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92.md
    /* 以下题目类型属于斐波那契数列问题，包括上述的两题 */
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
    // https://leetcode.com/problems/house-robber/description/
    public int rob(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        // 长度设为nums.length吧，便于阅读
        int[] dp = new int[nums.length + 1]; 
        dp[1] = nums[0];
        dp[2] = nums[0] > nums[1] ? nums[0] : nums[1];
        for (int i = 2; i < nums.length; i++) {
            dp[i+1] = Math.max(dp[i], dp[i-1]+nums[i]);
        }
        return dp[nums.length];
    }
    public int rob2(int[] nums) {
        int pre_1 = 0, pre_2 = 0, cur = 0;
        for (int i = 0; i < nums.length; i++) {
            cur = Math.max(pre_1, pre_2+nums[i]);
            pre_2 = pre_1;
            pre_1 = cur;
        }
        return pre_1;
    }
    // https://leetcode.com/problems/house-robber-ii/description/
    public int robCicle(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        if(nums.length == 2) return Math.max(nums[0], nums[1]);
        return Math.max(rob(nums, 0, nums.length-1), rob(nums, 1, nums.length));
    }
    private int rob(int[] nums, int left, int right) {
        int pre_1 = 0, pre_2 = 0, cur = 0;
        for (int i = left; i < right; i++) {
            cur = Math.max(pre_1, pre_2+nums[i]);
            pre_2 = pre_1;
            pre_1 = cur;
        }
        return pre_1;
    }
    // https://www.jianshu.com/p/60f0fe058ce8
    public static int errorEnvelopeNum(int n) {
        if(n <= 1) return 0;
        if(n == 2) return 1;
        return (n-1)*(errorEnvelopeNum(n-2)+errorEnvelopeNum(n-1));
    }
    /**
     题目描述：假设农场中成熟的母牛每年都会生 1 头小母牛，并且永远不会死。第一年有 1 只小母牛，从第二年开始，母牛开始生小母牛。每只小母牛 3 年之后成熟又可以生小母牛。给定整数 N，求 N 年后牛的数量。
     第 i 年成熟的牛的数量为： dp[i] = dp[i-1] + dp[i-3]
     */

    /* 菲波那切数列问题结束，以下是 矩阵路径 问题 */
    // 矩阵的最小路径和 https://leetcode.com/problems/minimum-path-sum/
    public int minPathSum(int[][] grid) {
        if(grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n]; // 记录每一列的最小路径；行由for循环控制，循环利用该数组
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(j == 0) dp[j] = dp[j]; // 只能由上一行走下来
                else if(i == 0) dp[j] = dp[j-1]; // 只能由左侧走过来
                else dp[j] = Math.min(dp[j-1], dp[j]); // 去左侧、上一行的最小值
                dp[j] += grid[i][j];
            }
        }
        return dp[n-1];
    }
    // https://leetcode.com/problems/unique-paths/
    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[j] = dp[j-1] + dp[j];
            }
        }
        return dp[n-1];
    }
    /*
    上述问题也可以看作是组合问题。机器人总共移动的次数S = M + N - 2，向下移动了D = m - 1，那么问题可以看成从S中取出D个位置的组合数量，这个问题的解为C(S, D)
     */
    public int uniquePaths2(int m, int n) {
        int S = m + n - 2;  // 总共的移动次数
        int D = m - 1;      // 向下的移动次数
        long ret = 1; // 避免溢出
        for (int i = 1; i <= D; i++) {
            ret = ret * (S - D + i) / i;
        }
        return (int) ret;
    }

    /* 矩阵路径 问题结束，以下是 数组区间 问题 */
    // 数组区间和 https://leetcode.com/problems/range-sum-query-immutable/
    // 数组中等差递增子区间的个数 https://leetcode.com/problems/arithmetic-slices/
    public int numberOfArithmeticSlices(int[] A) {
        if(A.length <= 2) return 0;
        int n = A.length;
        int[] dp = new int[n]; // dp[i]表示 以i为结尾 的递增数列个数
        for (int i = 2; i < n; i++) {
            if(A[i]-A[i-1] == A[i-1]-A[i-2]){
                dp[i] = dp[i-1] + 1;
            }
        }
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += dp[i];
        }
        return total;
    }
    /* 分割整数问题 */
    // https://leetcode.com/problems/integer-break/
    public int integerBreak(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        for (int i = 2; i < n+1; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(dp[i-j]*j, j*(i-j)));
            }
        }
        return dp[n];
    }
    /* 综合上下两题，总结，动态规划有时候就是最笨的方法，只是存储了之前的计算结果 */
    // https://leetcode.com/problems/perfect-squares/
    public int numSquares(int n) {
        List<Integer> squareNumberList = squareNumberGenerator(n);
        int[] dp = new int[n+1];
        dp[0] = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < n+1; i++) {
            min = Integer.MAX_VALUE;
            for (int squareNumber : squareNumberList) {
                if(squareNumber > i) break;
                min = Math.min(dp[i-squareNumber]+1, min);
            }
            dp[i] = min;
        }
        return dp[n];
    }
    // 产生完全平方数，小于等于n
    private List<Integer> squareNumberGenerator(int n) {
        List<Integer> squareNumberList = new ArrayList<>();
        int squareNumber = 1, diff = 3;
        while(squareNumber <= n){
            squareNumberList.add(squareNumber);
            squareNumber += diff;
            diff += 2;
        }
        return squareNumberList;
    }
    // https://leetcode.com/problems/decode-ways/
    public int numDecodings(String s) {
        int len = s.length();
        int[] dp = new int[len + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        int lastOne = 0, lastTwo = 0;
        for (int i = 2; i < len+1; i++) {
            lastOne = Integer.parseInt(s.substring(i-1, i));
            if(lastOne != 0) dp[i] += dp[i-1];
            if(s.charAt(i-2) == '0') continue;
            lastTwo = Integer.parseInt(s.substring(i-2, i));
            if(lastTwo <= 26) dp[i] += dp[i-2];
        }
        return dp[len];
    }
    public static void main(String[] args) {
        System.out.println(errorEnvelopeNum(4));
    }
}