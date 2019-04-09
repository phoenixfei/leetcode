package leetcode;

/**
 * recursion
 */
public class recursion {

    public static void main(String[] args) {
        System.out.println(mysum(100));
        System.out.println(recursionNum(100));
    }

    public static int mysum(int num) {
        return (1+num)*num/2;
    }
    public static int recursionNum(int num) {
        if(num == 1)return 1;
        return num + recursionNum(num-1);
    }
}