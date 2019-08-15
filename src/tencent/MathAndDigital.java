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

    public static String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int[] pos = new int[m + n];
       
        for(int i = m - 1; i >= 0; i--) {
            for(int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0'); 
                int p1 = i + j, p2 = i + j + 1;
                int sum = mul + pos[p2];
    
                pos[p1] += sum / 10;
                pos[p2] = (sum) % 10;

                for(int p : pos) System.out.print(p);
                System.out.println();
            }
        }  
        
        StringBuilder sb = new StringBuilder();
        for(int p : pos) if(!(sb.length() == 0 && p == 0)) sb.append(p);
        return sb.length() == 0 ? "0" : sb.toString();
    }
    // https://leetcode.com/problems/palindrome-number/solution/
    public boolean isPalindrome(int x) {
        if( x < 0 || ((x % 10) == 0 && x != 0) ) return false;
        int reverse = 0;
        while( x > reverse ){
            int num = x % 10;
            reverse = reverse * 10 + num;
            x = x / 10;
        }
        return reverse == x || reverse / 10 == x;
    }
    
    public static void main(String[] args) {
        // 注意，不要让数的大小超过int范围
        multiply("123", "45");
        // System.out.println(Integer.MIN_VALUE - 2);
        // System.out.println(Integer.MIN_VALUE);
        // int x = -123;
        // while (x != 0) {
        //     System.out.println(x%10);
        //     x = x / 10;
        //     System.out.println(x);
        // }
        /*
        // 计算n是否为2的幂（必须去掉0的情况）
            n & (n - 1) == 0  // 或
            (n & -n) == n
        */
        System.out.println( 0 & (0-1) );
        System.out.println( 0 & -0 );
    }
}