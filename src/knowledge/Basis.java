package knowledge;

/**
 * Knowledge
 */
public class Basis {
    //全局变量数组的初始化值;为对应类型的初始化值
    public static void initArr() {
        //https://blog.csdn.net/u013309870/article/details/75193592
        int[] a = new int[10];
        String[] b = new String[10];
        for(int i=0;i<10;i++) {
            System.out.print(a[i]+" ");
        }
        System.out.println();
        for(int i=0;i<10;i++){
            System.out.print(b[i]);
        }
    }
    //java加等于符号问题**下述输出值不同**
    //总结，尽量使用(加、减、乘...等于)
    public static void addEqu(){
        int ret = 111, ret2 = 3;
        int count = 2;
        System.out.println(count>>1);
        // int c = count>>1;
        ret = ret + count>>1;
        ret2 += count>>1;
        System.out.println(ret);
        System.out.println(ret2);
    }
    /**
     * 交换两个数的三种方法使用总结
     * 1.利用第三方变量
     * 2.利用两数之和
     * 3.利用异或：两个操作数的位中，相同则结果为0，不同则结果为1。
     * 总结：
     * 方式一是最常见的，可读性高，但是需要在内存中存放临时变量，但是对于现在来说，需要的内存空间很小，而且存放临时变量的内存很快就会释放，不存在问题。
     * 方式二有缺陷，当两个数相加之后，可能其结果超出了变量类型能表达的最大范围，这个时候结果就会出问题，不推荐使用
     * 方式三效率是最高的，但是可读性不是很好。
     * 在程序中我们应该尽可能的使用方式一，提高程序的可读性。但是需要了解方式三，以及方式三的原理。
     * 强调：
     * 由于java是值传递，某个方法调用上述三个方法时，都无法对数进行交换，解决方法，将交换函数写到方法体内，不作为单独方法存在；
     * 如果需要对数组中的两个数进行交换时，可以将数组、数1索引、数2索引，传进去swap函数，对数组元素进行交换
     * @param a
     * @param b
     */
    public static void swap(int a, int b) {
        System.out.println("a=" + a + ",b=" + b);
        int tep = b;
        b = a;
        a = tep;
        System.out.println("交换后，a=" + a + ",b=" + b);
    }
    public static void swap2(int a, int b){
        System.out.println("a=" + a + ",b=" + b);
        a = a+b;
        b = a-b;
        a = a-b;
        System.out.println("交换后，a=" + a + ",b=" + b);
    }
    public static void swap3(int a, int b){
        System.out.println("a=" + a + ",b=" + b);
        a ^= b;
        b ^= a;
        a ^= b;
        System.out.println("交换后，a=" + a + ",b=" + b);
    }
    public static void main(String[] args) {
        // initArr();
        // addEqu();
        // swap(15, 1);
        // swap2(15, 1);
        // swap3(15, 1);
        // swap3(2, 2);
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
    }
}