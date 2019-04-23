package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Leetcode {
    // https://leetcode.com/problems/container-with-most-water/
    // 使用两个指针，从左右两边进行遍历
    public int maxArea(int[] height) {
        int max_area = 0, l = 0, r = height.length - 1;
        while (l < r) {
            max_area = Math.max(max_area, (Math.min(height[l], height[r]) * (r - l)));
            if (height[l] < height[r]) {
                l++;
            } else {
                r--;
            }
        }
        return max_area;
    }

    // https://leetcode.com/problems/3sum/
    // 1.如果数据有重复，结果不需要重复，可以先进行排序；2.尽量不要多用for循环嵌套；3.两个指针左右开弓
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int length = nums.length;
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (int i = 0; i < length - 2; i++) {
            int target = -nums[i], l = i + 1, r = length - 1;
            while (l < r) {
                if (nums[l] + nums[r] < target)
                    l++;
                else if (nums[l] + nums[r] > target)
                    r--;
                else {
                    result.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    int l_num = nums[l];
                    int r_num = nums[r];
                    while (l < r && l_num == nums[l])
                        l++;
                    while (l < r && r_num == nums[r])
                        r--;
                }
            }
            while (i < length - 3 && nums[i] == nums[i + 1])
                i++;
        }
        return result;
    }

    // https://leetcode.com/problems/two-sum/discuss/6/My-(short)-Java-solution-O(n)-%2B-HashMap!
    // 使用map函数，来判断两个数是否相等
    public int[] twoSum(int[] nums, int target) {
        int[] ret = new int[2];
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    ret[0] = i;
                    ret[1] = j;
                    break;
                }
            }
            if (ret[1] != 0)
                break;
        }
        return ret;
    }

    // https://leetcode.com/problems/4sum/
    // 1.多重问题向下转型，简单化；2.记得先排除特殊情况；
    // 3.for循环中尽量不要使用return结束方法.下例i中可以break与return，j中只能break。
    // 所以统一用break结束循环，因为循环体外有return语句。
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ret = new ArrayList<List<Integer>>();
        if (nums.length < 4 || nums == null)
            return ret;
        Arrays.sort(nums);
        if (nums[nums.length - 1] * 4 < target || nums[0] * 4 > target)
            return ret;
        for (int i = 0; i < nums.length - 3; i++) {
            if (nums[i] * 4 > target)
                break;
            for (int j = i + 1; j < nums.length - 2; j++) {
                if (nums[i] + nums[j] * 3 > target)
                    break;
                int itarget = target - nums[i] - nums[j];
                int l = j + 1, r = nums.length - 1;
                while (l < r) {
                    if (nums[l] + nums[r] > itarget) {
                        r--;
                    } else if (nums[l] + nums[r] < itarget) {
                        l++;
                    } else {
                        ret.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        int l_num = nums[l];
                        int r_num = nums[r];
                        while (l < r && l_num == nums[l])
                            l++;
                        while (l < r && r_num == nums[r])
                            r--;
                    }
                }
                while (j < nums.length - 2 && nums[j] == nums[j + 1])
                    j++;
            }
            while (i < nums.length - 3 && nums[i] == nums[i + 1])
                i++;
        }
        return ret;
    }

    // https://leetcode.com/problems/3sum-closest/
    // 1.排序，定义左右指针；2.求最大最小值问题，可以对结果不停的赋值，覆盖旧值
    public int threeSumClosest(int[] nums, int target) {
        int ret = nums[0] + nums[1] + nums[nums.length - 1];
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int l = i + 1, r = nums.length - 1;
            while (l < r) {
                int temp = nums[i] + nums[l] + nums[r];
                if (temp < target) {
                    l++;
                } else {
                    r--;
                }
                // 将合适的值不停覆盖结果，使结果永远是最合适的，直至循环结束
                if (Math.abs(temp - target) < Math.abs(ret - target)) {
                    ret = temp;
                }
            }
        }
        return ret;
    }

    // https://leetcode.com/problems/divide-two-integers/
    // 双重while循环
    public int divide(int dividend, int divisor) {
        boolean isNegative = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0) ? true : false;
        long result = 0;
        long abs_dividend = Math.abs((long) dividend);
        long abs_divisor = Math.abs((long) divisor);
        while (abs_dividend >= abs_divisor) {
            long temp = abs_divisor, count = 1;
            while (temp <= abs_dividend) {
                temp = temp << 1;
                count = count << 1;
            }
            // result = result + (count<<1);注意+的优先级高于位运算符；
            // 尽量使用+=，避免上述问题；
            result += count >> 1;
            abs_dividend -= temp >> 1;
        }
        return isNegative ? (int) ~result + 1 : result > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) result;
    }

    // https://leetcode.com/problems/koko-eating-bananas/
    // 利用二分查找解决区间问题
    public int minEatingSpeed(int[] piles, int H) {
        int left = 1, right = getMax(piles);
        while (left <= right) {
            // 注意初始化的位置
            int mid = (left + right) / 2;
            int h = 0;
            for (int pile : piles) {
                h += (pile + mid - 1) / mid;
            }
            if (h > H) {
                left = mid + 1;
            } else if (h < H) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return left;
    }

    private int getMax(int[] piles) {
        int max = piles[0];
        for (int pile : piles) {
            max = Math.max(pile, max);
        }
        return max;
    }

    class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }
    // https://leetcode.com/problems/insert-interval/
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> ret = new ArrayList<Interval>();
        for (Interval it : intervals) {
            if (it.end < newInterval.start){
                ret.add(it);
            }else if(it.start > newInterval.end){
                ret.add(newInterval);
                newInterval = it;
            }else{
                newInterval.start = Math.min(newInterval.start, it.start);
                newInterval.end = Math.max(newInterval.end, it.end);
            }
        }
        ret.add(newInterval);
        return ret;
    }
    //https://leetcode.com/problems/merge-intervals/
    public List<Interval> merge(List<Interval> intervals) {
        if(intervals.size() <= 1)return intervals;
        //排序
        intervals.sort(new Comparator<Interval>(){
            @Override
            public int compare(Interval o1, Interval o2) {
                return Integer.compare(o1.start, o2.start);
            }  
        });
        List<Interval> ret = new ArrayList<Interval>();
        Interval retInterval = intervals.get(0);
        for (Interval it : intervals) {
            if(ret.size() == 0){
                ret.add(it);
            }else{
                if (it.end < retInterval.start) {
                    ret.add(it);
                } else if(it.start > retInterval.end){
                    ret.add(it);
                    retInterval = it;
                }else{
                    Interval addInterval = new Interval(Math.min(it.start, retInterval.start), Math.max(it.end, retInterval.end));
                    ret.remove(retInterval);
                    ret.add(addInterval);
                    retInterval = addInterval;
                }
            }
        }
        return ret;
    }
    // https://leetcode.com/problems/trapping-rain-water/
    /**
     Approach 1: Brute force
     Do as directed in question. For each element in the array, we find the maximum level of water it can trap after the rain, which is equal to the minimum of maximum height of bars on both the sides minus its own height.
     */
    public int trap(int[] height) {
        int ret = 0;
        for (int i = 0; i < height.length; i++) {
            int left_max = 0, right_max = 0;
            for(int j = i; j>=0; j--){
                left_max = Math.max(left_max, height[j]);
            }
            for (int k = i; k < height.length; k++) {
                right_max = Math.max(right_max, height[k]);
            }
            ret += Math.min(left_max, right_max) - height[i];
        }
        return ret;
    }
    /**
     Approach 2: Dynamic Programming
     In brute force, we iterate over the left and right parts again and again just to find the highest bar size upto that index. But, this could be stored. Voila, dynamic programming.
     */
    public int trapDP(int[] height) {
        // 请写上null与长度的判断
        if(height == null || height.length <= 1) return 0;
        int ret = 0;
        int len = height.length;
        int[] left_max = new int[len];
        int[] right_max = new int[len];
        left_max[0] = height[0];
        right_max[len-1] = height[len-1];
        // 可以都写i循环，除非内部循环
        for (int i = 1; i < len; i++) {
            left_max[i] = Math.max(left_max[i-1], height[i]);
        }
        for (int j = len - 2; j >= 0; --j) {
            right_max[j] = Math.max(right_max[j+1], height[j]);
        }
        for (int k = 0; k < len; k++) {
            ret += Math.min(left_max[k], right_max[k]) - height[k];
        }
        return ret;
    }
    public static void main(String[] args) {
        Leetcode leetcode = new Leetcode();
        int[] nums = { -3, -2, 5, 7 };
        System.out.println(leetcode.threeSum(nums));
    }
}