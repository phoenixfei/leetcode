package app;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1, 2, 5}, 11));
        System.out.println(coinChange(new int[]{2}, 3));
    }

    public static int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        int count = 0;
        for (int i = coins.length - 1; i >= 0 && amount > 0; i--) {
            while(amount - coins[i] >= 0){
                amount -= coins[i];
                count++;
            }
        }
        return amount == 0 ? count : -1;
    }
    
}