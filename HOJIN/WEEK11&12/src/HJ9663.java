import java.io.*;
import java.util.*;

public class HJ9663{
    private static int N;
    private static int cnt = 0;
    private static int[] col;  //i번째 행에서 퀸이 위치한 열의 번호 ->

    //Math.abs(row - i) == Math.abs(col[row] - col[i]) => 대각선에 있는지 조건 검사 매우 중요
    private static boolean canAttack(int row) {
        for(int i = 0; i < row; i++) {
            if (col[row] == col[i] || Math.abs(row - i) == Math.abs(col[row] - col[i])) return false;
        }
        return true;
    }

    private static void dfs(int row) {
        if(row == N) {
            cnt++;
            return;
        }

        for(int i = 0; i < N; i++) {
            col[row] = i;
            if(canAttack(row)) dfs(row + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        col = new int[N];

        dfs(0);

        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
}