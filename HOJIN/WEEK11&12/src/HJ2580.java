import java.io.*;
import java.util.*;

public class HJ2580 {
    static int[][] board = new int[9][9];
    static List<int[]> blanks = new ArrayList<>();
    static boolean solved = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 받기 및 빈칸 좌표 수집
        for (int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 0) blanks.add(new int[]{i, j});
            }
        }

        solve(0);
    }

    private static void solve(int idx) {
        if (idx == blanks.size()) {
            printBoard();
            solved = true;
            return;
        }

        int[] pos = blanks.get(idx);
        int x = pos[0], y = pos[1];

        for (int num = 1; num <= 9; num++) {
            if (isValid(x, y, num)) {
                board[x][y] = num;
                solve(idx + 1);
                if (solved) return;
                board[x][y] = 0; // 백트래킹
            }
        }
    }

    private static boolean isValid(int x, int y, int num) {
        // 가로, 세로 검사
        for (int i = 0; i < 9; i++) {
            if (board[x][i] == num || board[i][y] == num) return false;
        }

        // 3x3 박스 검사
        int startRow = (x / 3) * 3;
        int startCol = (y / 3) * 3;

        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {
                if (board[i][j] == num) return false;
            }
        }

        return true;
    }

    private static void printBoard() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int n : row) {
                sb.append(n).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}