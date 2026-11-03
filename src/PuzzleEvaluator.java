// PuzzleEvaluator.java
// 拼图核心逻辑的「自动化评测模块」
// 对应实习 JD 中的“自动化评测工具开发”：定义评测标准 -> 批量跑用例 -> 统计通过率
// 说明：本模块把游戏核心算法抽成可独立测试的纯函数（与 GameJF 中逻辑一致），
//       之后把 GameJF 里的判定/移动也改为调用这里，即可直接评测游戏本体。
public class PuzzleEvaluator {

    // 目标棋盘（胜利状态），与 GameJF 中的 win[][] 一致
    static final int[][] WIN = {
        {1, 2, 3, 4},
        {5, 6, 7, 8},
        {9, 10, 11, 12},
        {13, 14, 15, 0}
    };

    // 评测点1：胜利判定 —— 棋盘是否等于目标状态
    static boolean isSolved(int[][] b) {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (WIN[i][j] != b[i][j]) return false;
        return true;
    }

    // 评测点2：打乱合法性 —— 16 个格子必须恰好是 0~15 各出现一次
    static boolean isValidShuffle(int[][] b) {
        boolean[] seen = new boolean[16];
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++) {
                int v = b[i][j];
                if (v < 0 || v > 15 || seen[v]) return false;
                seen[v] = true;
            }
        return true;
    }

    // 评测点3：移动规则 —— 空格与相邻方块交换（dir: 0上 1下 2左 3右），越界则不动
    // 约定：blankX=列, blankY=行；board[行][列]
    static void move(int[][] b, int blankX, int blankY, int dir) {
        int nx = blankX, ny = blankY;
        if (dir == 0) ny--;       // 上
        else if (dir == 1) ny++;  // 下
        else if (dir == 2) nx--;  // 左
        else if (dir == 3) nx++;  // 右
        if (nx < 0 || nx > 3 || ny < 0 || ny > 3) return; // 越界不动
        int t = b[blankY][blankX];
        b[blankY][blankX] = b[ny][nx];
        b[ny][nx] = t;
    }

    // 极简断言框架，顺便统计通过率
    static int passed = 0, total = 0;
    static void check(String name, boolean cond) {
        total++;
        if (cond) passed++;
        System.out.println((cond ? "[✓] " : "[✗] ") + name);
    }

    public static void main(String[] args) {
        System.out.println("=== 拼图核心逻辑自动化评测 ===");

        // 用例1：已还原棋盘应判为胜利
        check("胜利判定-已还原棋盘应为true", isSolved(WIN));

        // 用例2：未还原棋盘应判为非胜利
        int[][] scrambled = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,0,15}};
        check("胜利判定-未还原棋盘应为false", !isSolved(scrambled));

        // 用例3：合法打乱（0~15 各一次）
        int[][] ok = {{0,1,2,3},{4,5,6,7},{8,9,10,11},{12,13,14,15}};
        check("打乱合法性-0~15各一次应为true", isValidShuffle(ok));

        // 用例4：非法打乱（缺 0、数字重复）应判不合法
        int[][] bad = {{1,1,2,3},{4,5,6,7},{8,9,10,11},{12,13,14,15}};
        check("打乱合法性-重复数字应为false", !isValidShuffle(bad));

        // 用例5：移动规则 —— 空格在右下角(3,3)
        int[][] board = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};
        move(board, 3, 3, 3); // 向右 -> 越界不动
        check("移动规则-越界不动应仍胜利", isSolved(board));
        move(board, 3, 3, 2); // 向左 -> 与 15 交换
        check("移动规则-左移后非胜利且空格到位",
              !isSolved(board) && board[3][2] == 0 && board[3][3] == 15);

        System.out.println("\n通过率: " + passed + "/" + total);
    }
}
