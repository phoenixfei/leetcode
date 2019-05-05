package knowledge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 *  Collections 工具类常用方法:
    1.排序
        下述三个方法都可以；此时o1和o2是引用类型变量；对于基本数据类型int，直接使用减号即可。
            查看源码可以发现，compareTo与compare都是Integer方法，只不过compare还是静态方法
            eturn o2.intValue() - o1.intValue();
            Integer.compare(o1.intValue(), o2.intValue());
            o2.compareTo(o1);
    2.查找,替换操作
    3.同步控制(不推荐，需要线程安全的集合类型时请考虑使用 JUC 包下的并发集合)
 */
public class ArraysCollctions {
    /**
     *    常用方法：
     *    void reverse(List list)//反转
     *    void shuffle(List list)//随机排序
     *    void sort(List list)//按自然排序的升序排序
     *    void sort(List list, Comparator c)//定制排序，由Comparator控制排序逻辑
     *    void swap(List list, int i , int j)//交换两个索引位置的元素
     *    void rotate(List list, int distance)//旋转。
     *    当distance为正数时，将list后distance个元素整体移到前面。当distance为负数时，将list的前distance个元素整体移到后面。
     */
    public static void collectionsSortMethod() {
        ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(-1);
		list.add(3);
		list.add(3);
		list.add(-5);
		list.add(7);
		list.add(4);
		list.add(-9);
		list.add(-7);
		System.out.println("原始数组:");
        System.out.println(list);
        //void reverse(List list)//反转
        Collections.reverse(list);
        System.out.println(list);
        //void rotate 旋转
        Collections.rotate(list, -2);
        System.out.println(list);
        Collections.rotate(list, 7);
        System.out.println(list);
        // void sort(List list),按自然排序的升序排序
        Collections.sort(list);
        System.out.println(list);
        //void shuffle(List list),随机排序
        Collections.shuffle(list);
        System.out.println(list);
        //定制排序的用法: 升序是前者减去后者，降序是后者减去前者。
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
            
        });
        System.out.println(list);
    }
    /**
     * 常用方法：
     * int binarySearch(List list, Object key)//对List进行二分查找，返回索引，注意List必须是有序的
        int max(Collection coll)//根据元素的自然顺序，返回最大的元素。 
        int max(Collection coll, Comparator c)//根据定制排序，返回最大元素，排序规则由Comparatator类控制
        int min(Collection coll)
        int min(Collection coll, Comparator c)
        void fill(List list, Object obj)//用指定的元素代替指定list中的所有元素。 
        int frequency(Collection c, Object o)//统计元素出现次数
        int indexOfSubList(List list, List target)//统计target在list中第一次出现的索引，找不到则返回-1，类比int lastIndexOfSubList(List source, list target).
        boolean replaceAll(List list, Object oldVal, Object newVal), 用新元素替换旧元素
     */
    public static void collectionsFindMethod() {
        ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(-1);
		list.add(3);
		list.add(3);
		list.add(-5);
		list.add(7);
		list.add(4);
		list.add(-9);
		list.add(-7);
		System.out.println("原始数组:");
        System.out.println(list);
        System.out.println("max：" + Collections.max(list));
        //查找元素在列表中首次出现的索引值; list.lastIndexOf(o);最后一次出现索引值
        System.out.println("元素首次出现索引值："+list.indexOf(3));
        //查找子串在列表中首次出现的位置
        System.out.println(Collections.indexOfSubList(list, Arrays.asList(3)));
        //二分查找，list必须有序，该方法会帮你排序，同样可以定制比较器
        System.out.println("二分查找："+Collections.binarySearch(list, -5));
        System.out.println("元素频率："+Collections.frequency(list, 3));
    }
    /**
     * Arrays的常用方法
     * sort(),可以定制比较器
     * binarySearch()
     * equals()
     * fill()
     * asList()
     * toString()
     */
    public static void arraysMethod() {
        //注意，要将int装箱成Integer才可以自定义比较器
        Integer a[] = { 1, 3, 2, 7, 6, 5, 4, 9 };
        Arrays.sort(a, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -o1.compareTo(o2);
            }
        });
        System.out.println(Arrays.toString(a));
        //binarySearch()，会自动帮你排序，也可以自定义比较器

        //字符串按照字母的自然顺序比较，跟字符个数无关
        String[] strs = { "abcdehg", "abcdefg", "abcdeag", "g" };
        Arrays.sort(strs);
        System.out.println(Arrays.toString(strs));//[abcdeag, abcdefg, abcdehg]
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        System.out.println(Arrays.toString(strs));//[g, abcdeag, abcdefg, abcdehg]
    }

    public static void arrayAddAdd() {
        int[] a = { 1, 3, 2, 7, 6, 5, 4, 9 };
        int[] b = new int[a.length];
        int i = 0 , j = 0;
        while (i<a.length) {
            b[j++] = a[i++];
        }
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.toString(b));
    }
    public static void main(String[] args) {
        // collectionsSortMethod();
        // collectionsFindMethod();
        // arraysMethod();
        arrayAddAdd();
    }
}