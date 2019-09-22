package leetcode;

/**
 * BinarySearch
 * https://github.com/CyC2018/CS-Notes/blob/master/docs/notes/Leetcode%20%E9%A2%98%E8%A7%A3%20-%20%E4%BA%8C%E5%88%86%E6%9F%A5%E6%89%BE.md
 * 注意：查找区间必须 有序
 * 二分查找，也成为折半查找，每次查找都将查找区间减半，因此时间复杂度为O(logn)
 */
public class BinarySearch {
    /**
     * 正常实现二分查找，获取指定元素在数组中的索引，不存在返回-1；
     * @param arr 已排好序的数组
     * @param key 待查找元素
     * @return 
     *  -1: 以一个错误码表示没有查找到key；
     *  left：将key插入到该区间，正确的插入位置。
     * 
     * mid = (right + left)/2; 这种情况可能出现加法溢出，即 和超出int范围。
     * 应使用 mid = left + (right - left)/2; 当然，除以2可以用右移一位代替；
     * 即最完美结果，也是官方推荐：mid = left + (right - left) >> 2;
     */
    public static int binarySearch(int[] arr, int key) {
        int left = 0, mid = 0, right = arr.length - 1;
        while(left <= right){
            mid = (right + left) / 2;
            if(arr[mid] == key) return mid;
            else if(arr[mid] < key) left = mid + 1;
            else if(arr[mid] > key) right = mid - 1;
        }
        if (arr[left] == key) return left;
        else return -1;
    }

    public static int binarySearch2(int[] arr, int key) {
        // right = arr.length - 1; 也行
        int left = 0, mid = 0, right = arr.length;
        while(left < right){
            mid = (right + left) / 2;
            if(arr[mid] < key) left = mid + 1;
            else right = mid;
        }
        if (arr[left] == key) return left;
        else return -1;
    }

    /**
     * 变种：在一个有重复元素的区间中查找key，返回最左边的位置
     * 该实现与正常实现有以下不同：
     *      1.循环条件为left < right; 若以<=循环，可能无法退出循环。
     *      2.right的赋值表达式为：right = mid;
     *      3.最后返回l而不是-1。
     * @param arr
     * @param key
     * @return 返回最左边的位置
     */
    public static int binarySearchL(int[] arr, int key) {
        // right = arr.length也行
        int left = 0, mid = 0, right = arr.length - 1;
        while (left < right) {
            mid = left + (right - left) / 2;
            if(arr[mid] >= key) right = mid;
            else left = mid + 1;
        }
        return arr[left] == key ? left : -1;
    }

    public static int binarySearchR(int[] nums, int target) {
        if (nums.length == 0) return -1;
        // 必须right = nums.length；因为返回left - 1，若target为最后一个元素
        int left = 0, right = nums.length;
    
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                left = mid + 1; // 注意
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            }
        }
        return left - 1; // 注意
    }

    // https://leetcode.com/problems/sqrtx/
    public int mySqrt(int x) {
        if(x<=1) return x;
        int l = 1, m = 0, h = x;
        int sqrt = 0; // 不要用乘法，用除法
        while (l <= h) {
            m = l + (h-l) / 2;
            sqrt = x / m;
            if(sqrt == x) return m;
            else if(sqrt > m) h = m - 1;
            else l = m + 1;
        }
        return h;
    }

    // https://leetcode.com/problems/find-smallest-letter-greater-than-target/
    public char nextGreatestLetter(char[] letters, char target) {
        int left = 0, mid = 0, right = letters.length - 1;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if(letters[mid] > target) right = mid - 1;
            else left = mid + 1;
        }
        if(left >= letters.length) return letters[0];
        return letters[left]; 
    }

    // https://leetcode-cn.com/problems/single-element-in-a-sorted-array/
    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1; 
        while (left < right) {
            int mid = left + (right - left) / 2;
            if ( mid % 2 == 1) mid --;
            if ( nums[mid] == nums[mid + 1]) left = mid + 2;
            else right = mid;
        }
        return nums[left];
    }

    public static void main(String[] args) {
        int[] arr = {0, 0, 1, 1, 2, 2, 3, 3, 4, 6, 9};
        int key = 1;
        // System.out.println(binarySearch(arr, key));
        System.out.println(binarySearch2(arr, key));
        // System.out.println(binarySearchL(arr, key));
        // System.out.println(binarySearchR(arr, key));
    }
}