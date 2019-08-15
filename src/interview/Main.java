package interview;
// package main
// 注意不要添加包名称，否则会报错。

import java.util.Scanner;

public class Main {

    public static int[] ret;
    public static void main(String args[]) {   
        Scanner cin = new Scanner(System.in);
        int N = cin.nextInt();
        for(int i = 0; i < N; i++) {
            int M = cin.nextInt();
            int[] gold = new int[M];
            for(int j = 0; j < M; j++) {
                gold[j] = cin.nextInt();
            }
        }
        cin.close();
    }

}

/*
1. 输入输出样例
public static void main(String args[]) {   
    Scanner cin = new Scanner(System.in);
    int N, M;
    while(cin.hasNextInt()) {
        N = cin.nextInt();
        M = cin.nextInt();
        Queue<Integer> cur = new LinkedList<>();
        Set<Integer> all = new HashSet<>();
        for(int i = 0; i < M; i++) {
            int a = cin.nextInt();
            int b = cin.nextInt();
            int c = cin.nextInt();
        }
    }
}
2. 
Scanner cin = new Scanner(System.in);
int N = cin.nextInt();
for(int i = 0; i < N; i++) {
    int M = cin.nextInt();
    int[] gold = new int[M];
    for(int j = 0; j < M; j++) {
        gold[j] = cin.nextInt();
    }
}
cin.close();
3. 
public static int count;
public static void main(String args[]) {   
    Scanner cin = new Scanner(System.in);
    int N;
    while(cin.hasNextInt()) {
        N = cin.nextInt();
        int[] colors = new int[N];
        for(int i = 0; i < N; i++) {
            colors[i] = cin.nextInt();
        }
        if(!isMul(colors, 0, N)) count++;
        dpColors(colors, 1, N);
        System.out.println(count);
    }
    cin.close();
}

private static void dpColors(int[] colors, int start, int end) {
    for (int i = start; i < end; i++) {
        if(!isMul(colors, 0, i+1)) {
            count++;
            dpColors(colors, i+1, end);
        }else{
            break;
        }
    }
}

private static boolean isMul(int[] colors, int start, int end) {
    int[] bitColor = new int[10];
    for (int i = start; i < end; i++) {
        if(bitColor[colors[i]] == 1) {
            return true;
        }
        bitColor[colors[i]] ++;
    }
    return false;
}
*/