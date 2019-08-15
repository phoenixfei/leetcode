package tencent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * BackTracking
 * 在计算机问题中，大量的问题都要使用递归算法，递归算法中非常经典的思想即为回溯法。
 * 递归调用的一个重要特征--返回。 回溯法是暴利解法的一个主要实现手段。
 * 
 * 这类思想通常应用在一类问题上，这类问题就是 树型问题。
 * 
 */
public class BackTracking {
    /* https://leetcode.com/problems/subsets/discuss/27281/ */
    // 1. 子集
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();
        addSets(ret, temp, nums, 0);
        return ret;
    }
    private static void addSets(List<List<Integer>> ret, List<Integer> temp, int[] nums, int start){
        ret.add(new ArrayList<>(temp));
        // System.out.println(ret.toString());
        for(int i = start; i < nums.length; i++){
            temp.add(nums[i]);
            System.out.println(temp.toString());
            addSets(ret, temp, nums, i+1);
            temp.remove(temp.size() - 1);
        }
    }
    // 2. 全排列
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        permuteBackTracking(ret, temp, nums);
        return ret;
    }
    private void permuteBackTracking(List<List<Integer>> ret, List<Integer> temp, int[] nums) {
        if(temp.size() == nums.length) {
            ret.add(new ArrayList<>(temp));
        }else{
            for (int i = 0; i < nums.length; i++) {
                if(temp.contains(nums[i])) continue;
                temp.add(nums[i]);
                permuteBackTracking(ret, temp, nums);
                temp.remove(temp.size()-1);
            }
        }
    }
    // 3. 组合总和
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }
    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, int remain, int start){
        if(remain < 0) return;
        else if(remain == 0) list.add(new ArrayList<>(tempList));
        else{ 
            for(int i = start; i < nums.length; i++){
                tempList.add(nums[i]);
                // not i + 1 because we can reuse same elements
                backtrack(list, tempList, nums, remain - nums[i], i); 
                tempList.remove(tempList.size() - 1);
            }
        }
    }
    // 4. 
    public List<List<String>> partition(String s) {
        List<List<String>> ret = new ArrayList<>();
        List<String> temp = new ArrayList<>();
        subPartition(ret, temp, s, 0);
        return ret;
    }
    private void subPartition(List<List<String>> ret, List<String> temp, String s, int start){
        if(start == s.length()){
            ret.add(new ArrayList<>(temp));
        }else{
            for(int i = start; i < s.length(); i++){
                if(isPalindrome(s, start, i)){
                    temp.add(s.substring(start, i+1));
                    subPartition(ret, temp, s, i+1);
                    temp.remove(temp.size() - 1);
                }
            }
        }
    }
    private boolean isPalindrome(String s, int start, int end){
        if(start > end) return false;
        while(start <= end){
            if(s.charAt(start++) != s.charAt(end--)){
                return false;
            }
        }
        return true;
    }
    // https://leetcode.com/problems/generate-parentheses/
    public List<String> generateParenthesis(int n) {
        List<String> ret = new ArrayList<String>();
        backTrackParenthesis(ret, "", 0, 0, n);
        return ret;
    }
    private void backTrackParenthesis(List<String> ret, String cur, int left, int right, int max){
        if(cur.length() == (max << 1)){
            ret.add(cur);
            return;
        }
        if(left < max){
            // cur += "("; 错误，因为无法回溯
            // 利用cur+"("，产生新的字符串，后续再使用cur时，即相当于回溯
            backTrackParenthesis(ret, cur+"(", left+1, right, max);
        }
        if(right < left){            
            backTrackParenthesis(ret, cur+")", left, right+1, max);
        }
    }    
    public static void main(String[] args) {
        subsets(new int[]{1,2,3});
    }
}