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

    // https://leetcode.com/problems/subsets/
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        subsetsBackTracking(ret, new ArrayList<Integer>(), nums, 0);
        return ret;
    }
    private void subsetsBackTracking(List<List<Integer>> list, ArrayList<Integer> temp, int[] nums, int start) {
        list.add(new ArrayList<>(temp));
        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            subsetsBackTracking(list, temp, nums, i+1);
            temp.remove(temp.size()-1);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ret = new ArrayList<>();
        int[] count = new int[nums.length];
        List<Integer> temp = new ArrayList<>();
        permuteBackTracking(ret, temp, nums, count);
        return ret;
    }
    private void permuteBackTracking(List<List<Integer>> ret, List<Integer> temp, int[] nums, int[] count) {
        if(temp.size() == nums.length) ret.add(new ArrayList<>(temp));
        else{
            for (int i = 0; i < count.length; i++) {
                if(count[i]==0){
                    if(temp.contains(nums[i])) continue;
                    temp.add(nums[i]);
                    count[i] = 1;
                    permuteBackTracking(ret, temp, nums, count);
                    temp.remove(temp.size()-1);
                    Arrays.fill(count, 0);
                }
            }
        }
    }

    public static void main(String[] args) {
        
    }
}