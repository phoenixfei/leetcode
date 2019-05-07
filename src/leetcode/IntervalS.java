package leetcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Interval
 */
public class IntervalS {
    
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
    
}