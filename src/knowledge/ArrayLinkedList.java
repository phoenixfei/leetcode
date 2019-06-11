package knowledge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * ArrayLinkedList https://github.com/Snailclimb/JavaGuide/blob/master/docs/java/%E8%BF%99%E5%87%A0%E9%81%93Java%E9%9B%86%E5%90%88%E6%A1%86%E6%9E%B6%E9%9D%A2%E8%AF%95%E9%A2%98%E5%87%A0%E4%B9%8E%E5%BF%85%E9%97%AE.md
 * Arraylist 与 LinkedList 异同:
 * 1.都线程不安全，2.底层数据结构不同，分别为数组和双向链表
 * 3.ArrayList支持快速随机访问
 * 
 * Vector线程安全的ArrayList
 */
public class ArrayLinkedList {

    public static void testArrayList(ArrayList<Integer> arrayList) {
        System.out.println(arrayList.get(1));
        Collections.sort(arrayList, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                //使用compareTo
                // return o2.intValue() - o1.intValue();
                return o2.compareTo(o1);
                
            }
            
        });
        System.out.println(arrayList);
    }

    public static void testLinkedList(LinkedList<Integer> linkedList) {
        System.out.println(linkedList.get(1));
        Collections.sort(linkedList, new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
                //下述三个方法都可以；查看源码可以发现，compareTo与compare都是Integer方法，只不过compare还是静态方法
                // return o2.intValue() - o1.intValue();
                // return Integer.compare(o1.intValue(), o2.intValue());
                return o2.compareTo(o1);
            }
            
        });
        System.out.println(linkedList);
        //iterator 与 foreach
        Iterator<Integer> it = linkedList.iterator();
        while(it.hasNext()){
            System.out.println(it.next());
        }
        for (Integer i : linkedList) {
            System.out.println(i);
        }
    }

    public static void testPriorityQueue() {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(5);pq.add(2);pq.add(4);pq.add(3);
        System.out.println(pq.toString());
        Iterator<Integer> it = pq.iterator();
        while (it.hasNext()) {
            System.out.print(it.next()+"\t");
        }
        System.out.println();
        while (!pq.isEmpty()) {
            System.out.print(pq.poll()+"\t");
        }
    }

    public static void testPriorityQueue2() {
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        
        });
        pq.add(5);pq.add(2);pq.add(4);pq.add(3);
        Iterator<Integer> it = pq.iterator();
        while (it.hasNext()) {
            System.out.print(it.next()+"\t");
        }
        System.out.println();
        System.out.println(pq.toString());
        while (!pq.isEmpty()) {
            System.out.print(pq.poll()+"\t");
        }
    }

    public static void testStack(){
        Stack<Integer> stack = new Stack<>();
        Queue<Integer> queue = new LinkedList<>();
        stack.add(1); stack.push(2); // 添加元素
        queue.add(1); queue.offer(2);
        stack.isEmpty(); queue.isEmpty(); // 判空
        stack.pop(); queue.poll(); // 取出栈顶元素
        stack.peek(); queue.peek(); // 查看栈顶元素
        stack.size(); queue.size(); // 查看元素个数
    }
    
    public static void main(String[] args) {
        testPriorityQueue();
        // ArrayList<Integer> arrayList = new ArrayList<Integer>();
        // LinkedList<Integer> linkedList = new LinkedList<Integer>();
        // arrayList.add(1);
        // arrayList.add(2);
        // arrayList.add(3);
        // arrayList.add(4);
        // linkedList.add(1);
        // linkedList.add(2);
        // linkedList.add(3);
        // linkedList.add(4);
        // testArrayList(arrayList);
        // testLinkedList(linkedList);
    }
}