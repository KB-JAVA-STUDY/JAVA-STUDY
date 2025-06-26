import java.io.*;
import java.util.*;

public class HJ1941{
    private static char[][] grid = new char[5][5];
    private static int result = 0;
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        //입력 받기
        for(int i = 0; i < 5; i++) {
            String curr = br.readLine();
            for(int j = 0; j < 5; j++) {
                grid[i][j] = curr.charAt(j);
            }
        }

        getCombination(0, 0, new ArrayList<>());
        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
    }

    private static void getCombination(int start, int depth, List<Integer> combination) {
        if (depth == 7) {
            if (isValid(combination)) result++;
            return;
        }

        for(int i = start; i < 25; i++) { //0 ~ 24까지 자릿수 7인 조합 구하기
            combination.add(i);
            getCombination(i + 1, depth + 1, combination);
            combination.remove(combination.size() - 1);
        }
    }

    private static boolean isValid(List<Integer> combination) {
        int cnt = 0;
        boolean[][] selected = new boolean[5][5];
        for (int idx : combination) {
            int x = idx / 5;
            int y = idx % 5;
            selected[x][y] = true;
            if (grid[x][y] == 'S') cnt++;
        }

        if (cnt < 4) return false; //이다솜파의 수가 4미만이면 7공주 불가능

        //연결됐는지 확인
        Deque<int[]> deque = new ArrayDeque<>();
        boolean[][] visited = new boolean[5][5];

        int xStart = combination.get(0) / 5;
        int yStart = combination.get(0) % 5;
        deque.offerLast(new int[]{xStart, yStart});
        visited[xStart][yStart] = true;

        int connected = 1;
        while(!deque.isEmpty()) {
            int[] curr = deque.pollFirst();
            int x = curr[0], y = curr[1];

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(!inRange(nx, ny)) continue;
                if(!visited[nx][ny] && selected[nx][ny]) {
                    visited[nx][ny] = true;
                    deque.offerLast(new int[]{nx, ny});
                    connected += 1;
                }
            }
        }

        return connected == 7;
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < 5 && 0 <= y && y < 5;
    }
}