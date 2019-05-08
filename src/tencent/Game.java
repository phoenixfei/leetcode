package tencent;

/**
 * Game
 */
public class Game {

    // https://leetcode.com/problems/nim-game/
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}