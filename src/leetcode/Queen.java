package leetcode;

/**
 * Queen https://blog.csdn.net/microopithecus/article/details/83832392
 * 行：row；列：column；
 https://mp.weixin.qq.com/s?__biz=MzU0MDg5OTYyOQ==&mid=2247483841&idx=1&sn=9819393f9142892312fa3aeba173c879&chksm=fb336183cc44e895b7e7a29ec52f2504a1a6aa925655bcf129f1c215a376fa67c302d747f253&mpshare=1&scene=24&srcid=0610K1pWllJUFk6b6yjbHIOK&key=7009efb4b025cbdb4d86de24275e5ad4ad2ff94ace7f90be9e1aae460295140781a24361b96e529bb8fd1eb1e91289535f76f84cf10bfe88c5c3d1dea1ab57f62325f43e2f37088538866f63872ff3e1&ascene=14&uin=NzE2Mjc2MDE2&devicetype=Windows+8&version=62060833&lang=zh_CN&pass_ticket=CgrODookre1tuVBq%2FTcRpm8oVkH3ZgMrqS%2FmfLmy4zjSfPvvK%2BaqcqiLkmZBUe%2F2
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