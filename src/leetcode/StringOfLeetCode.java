package leetcode;

import java.util.ArrayList;
import java.util.List;

class StringOfLeetCode {

    // KMP 算法
    // 1. 构造 pattern 的最大匹配数表
    public static int[] calculateMaxMatchLengths(String pattern) {
        int[] maxMatchLengths = new int[pattern.length()];
        int maxLength = 0;
        for (int i = 1; i < pattern.length(); i++) {            
            while (maxLength > 0 && pattern.charAt(maxLength) != pattern.charAt(i)) {
                maxLength = maxMatchLengths[maxLength-1];
            }
            if(pattern.charAt(i) == pattern.charAt(maxLength)) {
                maxLength++;
            }
            maxMatchLengths[i] = maxLength;
        }
        return maxMatchLengths;
    }
    // 2. 在 text 中寻找 pattern，返回所有匹配的位置开头
    public static List<Integer> search(String text, String pattern) {
        List<Integer> positions = new ArrayList<Integer>();
        int[] maxMatchLengths = calculateMaxMatchLengths(pattern);
        int count = 0;
        for (int i = 0; i < text.length(); i++) {
            while (count > 0 && pattern.charAt(count) != text.charAt(i)) {
                count = maxMatchLengths[count - 1];
            }
            if(pattern.charAt(count) == text.charAt(i)) {
                count++;
            }
            if(count == pattern.length()){
                positions.add(i - pattern.length() + 1);
                count = maxMatchLengths[count - 1];
            }
        }
        return positions;
    }
    public static void main(String[] args) {
        System.out.println(search("abbaabbaaba", "ab").toString());
    }
}