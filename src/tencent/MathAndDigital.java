package tencent;

/**
 * MathAndDigital
 */
public class MathAndDigital {
    // https://leetcode.com/problems/reverse-integer/
    public int reverse(int x) {
        int ret = 0;
        int left = 0;
        int flag = x > 0 ? 1 : -1;
        x = Math.abs(x);
        while (x != 0) {
            left = x % 10;
            x = x / 10;
            if(ret > (Integer.MAX_VALUE - left)/10){
                return 0;
            }
            ret = ret * 10 + left;
        }
        return  ret * flag;
    }
    
    public static void main(String[] args) {
        // 注意，不要让数的大小超过int范围
        System.out.println(Integer.MIN_VALUE - 2);
        System.out.println(Integer.MIN_VALUE);
        int x = -123;
        while (x != 0) {
            System.out.println(x%10);
            x = x / 10;
            System.out.println(x);
        }
    }
}