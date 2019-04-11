package leetcode;

import java.util.Arrays;

/**
 * DynamicProgarmming
 * Those who cannot remember the past are condemned to repeat it.
 * https://blog.csdn.net/u013309870/article/details/75193592
 * 动态规划的核心是记住以往求过的解：1.自顶向下的备忘录法；2.自底向上的动态规划
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
    public static void main(String[] args) {
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
    }
    /* 斐波拉切数列与切割钢条问题总结：
        1. 利用自顶向下(内部包含一个递归)，该方法比自底向上少一个循环。原因即体现在递归上。
        2. 动态规划的两种解法（自顶向下与自底向上），都利用一个数组存储了已经计算出来的节点信息，即比递归解法少了空间消耗。
        3. 如果需要保存的节点个数确定，对比斐波拉契数列自底向上问题优化解法，可以进一步降低空间消耗。
     */
}