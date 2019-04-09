package app;

public class App {

    public static void F(double f, double r) {
        System.out.println(f);
        System.out.println(r);
        double f1 = 2 * f * r / (f + r);
        System.out.println(f1);
    }

    public static void sentiment() {
        double[] pos = { 12.56, 5.09, 13.24, 7.13, 19.10, 5.69, 8.31 };
        double[] neg = { 2.21, 9.17, 3.31, 3.48, 6.45, 1.53, 2.72, };
        System.out.println("sum：" + (sumArr(pos)+sumArr(neg)));
        System.out.println("*****************");
        for (int i = 0; i < pos.length; i++) {
            System.out.println("第"+i+"组的和："+(pos[i]+neg[i]));
            System.out.println("第"+i+"组的商："+(pos[i]/neg[i]));
        }
    }
    private static double sumArr(double[] arr){
        double ret = 0;
        for (double num : arr) {
            ret += num;
        }
        return ret;
    }
    public static void main(String[] args) throws Exception {
        // System.out.println("Hello Java");
        // F(0.8483, 0.60);
        F(87.49 / 100, 79.23 / 100);
        F(81.02 / 100, 88.67 / 100);
        // sentiment();
    }
}