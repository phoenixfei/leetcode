package tencent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * ArrayString
 */
public class ArrayString {
    //https://leetcode.com/problems/two-sum/
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
    public static void main(String[] args) {
        
    }
}