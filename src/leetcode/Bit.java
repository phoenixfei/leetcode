package leetcode;

/**
 * Bit 位操作 https://www.jianshu.com/p/b677858bc085 基本的位操作有 与 、 或 、 异或 、 取反 、 左移 、
 * 右移 共 6 种。 & 与 两个位都为1时，结果才为1. | 或 两个位都为0时，结果才为0. ^ 异或 两个位相同时为0，相异为1.
 * 任何数与0异或不变，与1异或相当于取反 ~ 取反 0变1，1变0. << 左移 各二进位全部左移若干位，高位丢弃，低位补0. >> 右移
 * 各二进制全部右移若干位，左边用原有标志位补充，右边超出部分舍弃. >>> 无符号右移 左边部分总是以0补充，右边部分舍弃.
 * 
 * 注意 1、位操作只能用于 整形 数据。 2、位操作的优先级比较低，因此尽量使用 扩号 来确保运算顺序。 
 * 
 * 位操作的一些小技巧 1、判断奇偶 2、交换两数 3、变换符号 4、求绝对值
 */
public class Bit {
    // 判断奇偶
    public static boolean isOven(int i) {
        if ((i & 1) == 0)
            return true; // a % 2 == 0
        else
            return false;
    }

    // 交换两数
    public static void swap(int a, int b) {
        System.out.println("a = " + a + "\tb = " + b);
        a = a ^ b; // a ^= b
        b = a ^ b;
        a = a ^ b;
        System.out.println("a = " + a + "\tb = " + b);
    }

    /**
     * 变换符号：正变负，负变正 根据正数与负数在计算机中的存储方式，可知：交换符号可通过 按位取反，末位加一
     */
    public static int exchangeSymbol(int a) {
        return (~a) + 1;
    }

    /**
     * 求绝对值 首先，通过 a >> 31 取得符号位，再根据交换符号中的方法求绝对值
     */
    public static int abs(int a) {
        int sign = a >> 31;
        return sign == 0 ? a : ~a + 1;
    }

    /**
     * 求绝对值 利用异或 首先，通过 a >> 31 取得符号位，0或者-1。再根据a与sign的异或值再减去sign。
     * 如果sign为0，值还为a；如果sign为-1，相当于按位取反(符号位参与运算)，末位加1.
     */
    public static int absXOR(int a) {
        int sign = a >> 31;
        return (a ^ sign) - sign;
    }

    /**
     * 其它小技巧
     * 
     */
    public static void others() {
        System.out.println("int型最大值：");
        System.out.println(~(1 << 31));
        System.out.println((1 << 31) - 1);

        System.out.println("int型最小值：");
        System.out.println((1 << 31));
        System.out.println((1 << -1));

        System.out.println("long型最大值：");
        System.out.println(~((long) 1 << 31));

        // 乘以2运算
        System.out.println(10 << 1);

        // 除以2运算(负奇数的运算不可用)
        System.out.println(10 >> 1);

        // 乘以2的m次方
        System.out.println(10 << 2);

        // 除以2的m次方
        System.out.println(16 >> 2);

        int n = 0, m = 6;
        // 从低位到高位.将n的第m位置为1
        // 将1左移m-1位找到第m位，得到000...1...000, n在和这个数做或运算
        System.out.println(n | (1 << (m - 1)));

        // 将1左移m-1位找到第m位，取反后变成111...0...1111, n再和这个数做与运算
        System.out.println(n & ~(0 << (m - 1)));
    }

    public static void main(String[] args) {
        // 计算机中的原码反码和补码 https://blog.csdn.net/code_caq/article/details/68925861
        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE));
        System.out.println(5 << 1);
        System.out.println(5 >> 1);
        System.out.println(-5 << 1);
        System.out.println(-5 >> 1);
        // System.out.println("判断奇偶：");
        // for (int i = -5; i < 5; i++) {
        //     System.out.println(i + "是偶数吗？" + isOven(i));
        // }
        // System.out.println("交换两数：");
        // swap(5, 2);
        // System.out.println("交换两数的符号：");
        // for (int i = -5; i < 5; i++) {
        //     System.out.println(i + "交换符号后为：" + exchangeSymbol(i));
        // }
        // System.out.println("求绝对值：");
        // for (int i = -5; i < 5; i++) {
        //     System.out.println(i + "的绝对值为：" + abs(i));
        // }
        // for (int i = -5; i < 5; i++) {
        //     System.out.println(i + "的绝对值为：" + absXOR(i));
        // }
    }
}