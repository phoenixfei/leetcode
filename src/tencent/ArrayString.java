package tencent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * ArrayString
 */
public class ArrayString {
    // https://leetcode.com/problems/two-sum/
    public int[] twoSum(int[] nums, int target) {
        int[] ret = new int[2];
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i++){
            if(!map.containsKey(nums[i])){
                map.put(target-nums[i], i);
            }else{
                ret[0] = map.get(nums[i]);
                ret[1] = i;
            }
        }
        return ret;  
    }
    // https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len = nums1.length + nums2.length;
        if(len == 0) return 0;
        if(len == 1) return nums1.length == 1 ? nums1[0] : nums2[0];
        int count =  0, i = 0, j = 0;
        int[] rets = new int[len];
        while (i < nums1.length && j < nums2.length ) {
            rets[count++] = nums1[i] < nums2[j] ? nums1[i++] : nums2[j++];
        }
        while(i < nums1.length)rets[count++] = nums1[i++];
        while(j < nums2.length)rets[count++] = nums2[j++];
        System.out.println(Arrays.toString(rets));
        if(len % 2 == 0){
            return (rets[len/2] + rets[len / 2 - 1])/2.0;
        }else{
            return rets[len / 2];
        }
    }
    // https://leetcode-cn.com/problems/longest-palindromic-substring/
    public String longestPalindrome(String s) {
        if(s == null || s.length() < 1) return "";
        int len = s.length();
        boolean[][] dp = new boolean[len][len];
        int left_index = 0, right_index = 1, max_len = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j <= i; j++) {
                if(i-j == 0) dp[j][i] = true;
                else if(i-j == 1 && s.charAt(i) == s.charAt(j) ) dp[j][i] = true;
                else if(i-j >= 2 && s.charAt(i) == s.charAt(j) && dp[j+1][i-1]) dp[j][i] = true;
                if(max_len <= i+1-j && dp[j][i]){
                    max_len = i+1-j; left_index = j; right_index = i+1;
                }
            }
        }
        return s.substring(left_index, right_index);
    }
    /* 
    https://leetcode.com/problems/string-to-integer-atoi/
    字符变整数的两种方法，其一，利用Character；其二，'8'-'0';
    判断一个整数是否越过int边界，请在循环内部判断，注意变形。n > (Integer.MAX_VALUE - d) / 10
    */
    public int myAtoi(String str) {
        String s = str.trim().split(" ")[0];
        if(s.length() < 1) return 0;
        double dight = 0;
        int flag = 1;
        char firstChar = s.charAt(0);
        if(firstChar == '-') flag = -1;
        else if(firstChar == '+') flag = 1;
        else if(Character.isDigit(firstChar)) dight = Character.getNumericValue(firstChar);
        else return 0;
        if(s.length() == 1) return (int) dight;
        for (int i = 1; i < s.length(); i++) {
            if(Character.isDigit(s.charAt(i))){
                dight = dight * 10 + Character.getNumericValue(s.charAt(i));
            }else{
                break;
            }
        }
        if (flag == 1) return dight > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int)dight;
            return -dight <= Integer.MIN_VALUE ? Integer.MIN_VALUE : -(int)dight;
    }
    // https://leetcode.com/problems/longest-common-prefix/
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) return "";
        int i = 0;
        boolean flag = true;
        try {
            while(flag){
                char temp = strs[0].charAt(i);
                for (String str : strs) {
                    if(str.charAt(i) != temp){
                        flag = false;
                        break;
                    }
                }
                if(flag) i++;
            }
        } catch (IndexOutOfBoundsException e) {
        }
        if(i <= 0) return "";
        return strs[0].substring(0,i);
    }
    // 解法2，注意for加while循环，里面的while循环的结束条件
    public String longestCommonPrefix2(String[] strs) {
        if (strs.length < 1 || strs == null) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        //find the shortest String
        int shortest = 0;
        int len = strs[0].length();
        for (int i = 1; i < strs.length; i++) {
            int curLen = strs[i].length();
            if (curLen < len) {
                len = curLen;
                shortest = i;
            }
        }
        //find the longest common prefix
        String sub = strs[shortest];
        for (int i = 0; i < strs.length; i++) {
            while (strs[i].indexOf(sub) != 0) {
                sub = sub.substring(0, sub.length()-1);
            }
        } 
        return sub;
    }

    public boolean isValid(String s) {
        char[] arr = s.toCharArray();
        Stack<Character> stack = new Stack<Character>();
        for (char c: arr) {
            if(stack.isEmpty()){
                stack.push(c);
            }else{
                char top = stack.peek();
                if(c - top == 1 || c - top == 2){
                    stack.pop();
                }else{
                    stack.push(c);
                }
            }
        }
        if(stack.isEmpty()) return true;
        return false;
    }
    // https://leetcode.com/problems/merge-sorted-array/
    // while(n>0) A[m+n-1] = (m==0||B[n-1] > A[m-1]) ? B[--n] : A[--m];
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] ret = new int[nums1.length];
        int i = 0, j = 0, k = 0;
        while(i<=m-1 && j<=n-1){
            if(nums1[i] < nums2[j]){
                ret[k++] = nums1[i++];
            }else{
                ret[k++] = nums2[j++];
            }
        }
        while (i <= m-1) {
            ret[k++] = nums1[i++];
        }
        while (j <= n-1) {
            ret[k++] = nums2[j++];
        }
        // System.out.println(Arrays.toString(ret));
        // 对数组进行赋值
        System.arraycopy(ret, 0, nums1, 0, ret.length);
        // k = 0;
        // while(k < nums1.length){
        //     nums1[k++] = ret[k++];
        // }
    }
    // https://leetcode.com/problems/spiral-matrix/
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ret = new ArrayList<Integer>();
        // 注意极端情况判断，需return ret，而不是null。
        if(matrix.length == 0) return ret;
        int m = matrix.length, n = matrix[0].length;
        int r1 = 0, c1 = 0, r2 = m - 1, c2 = n - 1;
        while (r1 <= r2 && c1 <= c2) {
            for(int i = c1; i <= c2; i++){
                ret.add(matrix[r1][i]);
            }
            for(int i = r1+1; i <= r2; i++){
                ret.add(matrix[i][c2]);
            }
            // 需要进行判断
            if(r1 < r2 && c1 < c2){
                for (int i = c2-1; i >= c1; --i) {
                    ret.add(matrix[r2][i]);
                }
                for (int i = r2-1; i > r1; --i) {
                    ret.add(matrix[i][c1]);
                }
            }
            r1++; c1++; r2--; c2--;
        }
        return ret;
    }
    public static void main(String[] args) {
        
    }
}