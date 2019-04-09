package leetcode;

import java.util.Arrays;

/**
 * 注意，java是值引用，所以不要使用返回值，直接对数组对象进行修改即可。
 * sorting
 */
public class sorting {
    //冒泡排序
    //i循环控制已排序的个数，j循环控制参与排序的元素，由于有j+1，故if判断中-1-i
    public static void bubble(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr.length-1-i; j++){
                //改变正负可改变排序方式
                if(arr[j] > arr[j+1]){
                    swap(arr, j, j+1);
                }
            }
        }
    }
    //插入排序
    public static void insertion(int[] arr) {
        int current = 0, pre_index = 0;
        for (int i = 1; i < arr.length; i++) {
            current = arr[i];
            pre_index = i;
            //注意while循环中两个判断的位置
            while (pre_index > 0 && current < arr[pre_index-1]) {
                //写法帅气
                arr[pre_index] = arr[--pre_index];
            }
            arr[pre_index] = current;
        }
    }
    //快速排序
    public static void quick(int [] arr, int left, int right) {
        if(left >= right-1)return;
        int pivot = arr[left];
        int i = left;
        for(int j = left+1; j<right; j++){
            //交换符号，可改变排序顺序
            if(arr[j] < pivot){
                swap(arr, j, i+1);
                i++;
            }
        }
        swap(arr, left, i);
        quick(arr, left, i);
        quick(arr, i+1, right);
    }
    private static void swap(int[] arr, int a_index, int b_index){
        if(arr[a_index] == arr[b_index])return;
        arr[a_index] ^= arr[b_index];
        arr[b_index] ^= arr[a_index];
        arr[a_index] ^= arr[b_index];
    }
    public static void merge(int[] arr, int[] temp, int left, int right) {
        //在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        if(left >= right-1)return;
        int mid = (left + right)/2;
        merge(arr, temp, left, mid);
        merge(arr, temp, mid, right);
        int i = left, j = mid, t=0;
        while (i < mid && j < right) {
            if(arr[i]<arr[j]){
                //数组中的++，先赋值，再+1
                temp[t++] = arr[i++];
            }else{
                temp[t++] = arr[j++];
            }
        }
        while(i < mid)temp[t++] = arr[i++];
        while(j < right)temp[t++] = arr[j++];
        t = 0;
        //利用left<right,判断，是将部分排序后的元素返回原数组
        while(left < right)arr[left++] = temp[t++]; 
    }

    public static void main(String[] args) {
        int[] arr = {3,44,38,5,47,15,36,26,27,2,46,4,19,50,48};
        int[] temp = new int[arr.length];
        //插入
        // insertion(arr);
        // System.out.println(Arrays.toString(arr));
        // quick(arr, 0, arr.length);
        merge(arr, temp, 0, arr.length);
        System.out.println(Arrays.toString(arr));
    }
}