package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 注意，java是值引用，所以不要使用返回值，直接对数组对象进行修改即可。 Sorting
 * https://www.cnblogs.com/onepixel/articles/7674659.html
 */
public class Sorting {
    // 冒泡排序
    // i循环控制已排序的个数，j循环控制参与排序的元素，由于有j+1，故if判断中-1-i
    public static void bubble(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                // 改变正负可改变排序方式
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    // 选择排序
    public static void selection(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int point = i;
            for (int j = i+1; j < arr.length; j++) {
                if(arr[point] > arr[j]) point = j;
            }
            swap(arr, i, point);
        }
    }

    // 插入排序
    public static void insertion(int[] arr) {
        int current = 0, pre_index = 0;
        for (int i = 1; i < arr.length; i++) {
            current = arr[i]; // 必须记录下当前值
            pre_index = i;
            // 注意while循环中两个判断的位置
            while (pre_index > 0 && current < arr[pre_index - 1]) {
                // 写法帅气
                arr[pre_index] = arr[--pre_index];
            }
            arr[pre_index] = current;
        }
    }

    // 希尔排序 https://www.cnblogs.com/chengxiao/p/6104371.html
    // 子排序问题请仔细阅读第二层for循环
    public static void shell(int[] arr) {
        int len = arr.length;
        int j = 0;
        for (int step = len / 2; step > 0; step /= 2) {
            for (int i = step; i < len; i++) {
                int current = arr[i];
                j = i;
                while (j - step >= 0 && current < arr[j - step]) {
                    arr[j] = arr[j-step];
                    j -= step;
                }
                arr[j] = current;
            }
        }
    }

    // 快速排序
    public static void quick(int[] arr, int left, int right) {
        if (left >= right - 1)
            return;
        int pivot = arr[left];
        int i = left;
        for (int j = left + 1; j < right; j++) {
            // 交换符号，可改变排序顺序
            if (arr[j] < pivot) {
                swap(arr, j, ++i);
                // i++;
            }
        }
        swap(arr, left, i);
        quick(arr, left, i);
        quick(arr, i + 1, right);
    }

    private static void swap(int[] arr, int a_index, int b_index) {
        if (arr[a_index] == arr[b_index])
            return;
        arr[a_index] ^= arr[b_index];
        arr[b_index] ^= arr[a_index];
        arr[a_index] ^= arr[b_index];
    }

    // 归并排序
    public static void merge(int[] arr, int[] temp, int left, int right) {
        // 在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        // sort arr
        if (left >= right - 1)
            return;
        int mid = (left + right) / 2;
        merge(arr, temp, left, mid);
        merge(arr, temp, mid, right);
        // merge arr
        int i = left, j = mid, t = 0;
        while (i < mid && j < right) {
            if (arr[i] < arr[j]) {
                // 数组中的++，先赋值，再+1
                temp[t++] = arr[i++];
            } else {
                temp[t++] = arr[j++];
            }
        }
        while (i < mid)
            temp[t++] = arr[i++];
        while (j < right)
            temp[t++] = arr[j++];
        t = 0;
        // 利用left<right判断，是将部分排序后的元素返回原数组
        while (left < right)
            arr[left++] = temp[t++];
    }

    // 堆排序
    public static void heap(int[] arr) {
        int len = arr.length - 1;
        int beginIndex = (len - 1) >> 1;
        for (int i = beginIndex; i >= 0; --i) {
            maxHeapify(i, len, arr);
        }
        for (int i = len; i > 0; i--) {
            swap(arr, 0, i);
            maxHeapify(0, i-1, arr);
        }
    }

    private static void maxHeapify(int index, int len, int[] arr) {
        int left = (index << 1) + 1; // 左子节点索引
        int right = left + 1;
        if (left > len) return; // 左节点超过长度，退出
        int max = left;
        // 右节点超出，就只判断左节点
        if(right <= len && arr[right] > arr[left]) max = right;
        if(arr[index] < arr[max]) {
            swap(arr, index, max);
            maxHeapify(max, len, arr);
        }
    }

    // topk
    public static int topk(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : arr) {
            if(pq.size() < k || num > pq.peek()) pq.offer(num);
            if(pq.size() > k) pq.poll();
        }
        return pq.peek();
    }
    // 自定义堆实现topk大问题
    public static int topk2(int[] arr, int k) {
        int[] heap = new int[k];
        for (int i = 0; i < k; i++) {
            heap[i] = arr[i];
        }
        int len = k - 1;
        int index = (len - 1) >> 1;
        for (int i = index; i >= 0; --i) {
            minHeapify(i, len, heap);
        }
        for (int i = k; i < arr.length; i++) {
            if(arr[i] > heap[0]){
                heap[0] = arr[i];
                minHeapify(0, len, heap);
            }
        }
        return heap[0];
    }
    // 小顶堆
    private static void minHeapify(int index, int len, int[] arr) {
        int left = (index << 1) + 1;
        int right = left + 1;
        if(left > len) return;
        int min = left;
        if(right <= len && arr[right] < arr[min]) min = right;
        if(arr[min] < arr[index]){
            swap(arr, index, min);
            minHeapify(min, len, arr);
        }
    }
    // 利用快排解决topk问题
    public static int topk3(int[] arr, int k) {
        return quickSelect(arr, k, 0, arr.length-1);
    }
    public static int topk4(int[] arr, int k) {
        int position = position(arr, 0, arr.length-1);
        while (position != k - 1) {
            if(position > k - 1) position = position(arr, 0, position-1);
            if(position < k - 1) position = position(arr, position+1, arr.length-1);
        }
        return arr[k-1];
    }
    // quickSelect
    private static int quickSelect(int[] arr, int k, int left, int right) {
        if(left == right) return arr[left];
        int position = position(arr, left, right);
        if(position - left == k - 1) return arr[position];
        else if (position - left > k - 1) return quickSelect(arr, k, left, position-1);
        else return quickSelect(arr, k-1-position+left, position+1, right);
    }
    // 得到position
    private static int position(int[] arr, int left, int right) {
        int pivot = arr[left];
        int position = left;
        for (int i = left+1; i <= right; i++) {
            if(arr[i] > pivot){
                swap(arr, i, ++position);
            }
        }
        swap(arr, position, left);
        return position;
    }

    public static void main(String[] args) {
        int[] arr = { 3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48 };
        System.out.println(topk(arr, 5));
        System.out.println(topk2(arr, 4));
        System.out.println(topk3(arr, 4));
        System.out.println(topk4(arr, 12));
        
        // int[] temp = new int[arr.length];
        // insertion(arr);
        // shell(arr);
        // selection(arr);
        // System.out.println(Arrays.toString(arr));
        // quick(arr, 0, arr.length);
        heap(arr);
        // merge(arr, temp, 0, arr.length);
        System.out.println(Arrays.toString(arr));
    }
}