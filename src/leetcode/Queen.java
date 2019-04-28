package leetcode;

/**
 * Queen https://blog.csdn.net/microopithecus/article/details/83832392
 * 行：row；列：column；
 */
class Queen {

    private static final int N = 8; // n皇后
    private static int[][] chessBoard = new int[N][N];
    private int count = 1;

    // 打印出n中解法
    public void settleQueen(int n) {
        for (int col = 0; col < N; col++) { // 判断每一列
            // 每一次都将改行初始化为0
            for(int i = 0; i < N; i++) chessBoard[n][i] = 0;
            // 判断n行i列是否合格
            if(checkN(n, col)){
                chessBoard[n][col] = 1;
                if(n == N-1) {
                    printQueen();
                    System.out.println(count++);
                }
                else settleQueen(n+1);
            }
        }
    }
    /* 得到一种解法；注意，一种解法要用flag控制结束递归
    public boolean settleQueen(int n) {
        if(n == N) return true; // n为判断的行数
        for (int col = 0; col < N; col++) { // 判断每一列
            // 每一次都将改行初始化为0
            for(int i = 0; i < N; i++) chessBoard[n][i] = 0;
            // 判断n行i列是否合格
            if(checkN(n, col)){
                chessBoard[n][col] = 1;
                if(settleQueen(n+1)) return true;
            }
        }
        return false;
    }
    */
    // 检查row行，col列的元素能否放置皇后
    private boolean checkN(int row, int col){
        for(int i = 0; i < row; i++){
            if(chessBoard[i][col] == 1) return false;
            if(col-1-i >= 0 && chessBoard[row-1-i][col-1-i] == 1) return false;
            if(col+1+i < N && chessBoard[row-1-i][col+1+i] == 1) return false;
        }
        return true;
    }
    // 打印
    private void printQueen() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(chessBoard[i][j]);                
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Queen queen = new Queen();
        queen.settleQueen(0);
    }
}