package leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * GreedyAlgorithm
 * 保证每次操作都是局部最优的，并且最后得到的结果是全局最优的。
 */
public class GreedyAlgorithm {

    // https://leetcode.com/problems/assign-cookies/
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int gIndex = 0, sIndex = 0;
        while (gIndex < g.length && sIndex < s.length) {
            if(g[gIndex] <= s[sIndex]) gIndex++;
            sIndex++;
        }
        return gIndex;
    }
    // https://leetcode.com/problems/non-overlapping-intervals/
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        // 二维数组排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int ret = 0, end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if(intervals[i][0] < end){
                ret++;
            }else{
                end = intervals[i][1];
            }
        }
        return ret;
    }
    // https://leetcode-cn.com/problems/minimum-number-of-arrows-to-burst-balloons/
    public int findMinArrowShots(int[][] points) {
        if(points.length == 0) return 0;
        Arrays.sort(points, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }           
        });
        int ret = 1, end = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if(points[i][0] > end){
                ret++;
                end = points[i][1];
            }
        }
        return ret;
    }

}