import java.io.*;
import java.util.*;

public class HJ17144{
    private static int R, C, T, up, down;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};
    private static int[][] grid;


    private static boolean inRange(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }

    //방안의 미세먼지 개수 세기
    private static int checkDust() {
        int cnt = 0;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(grid[i][j] > 0) cnt += grid[i][j];
            }
        }
        return cnt;
    }

    private static void spread() {
        //저장을 위한 임시 배열 생성
        int[][] temp = new int[R][C];
        Deque<int[]> deque = new ArrayDeque<>(); //원본배열에서 움직이고 나머지 값을 더하기 위한 deque

        //배열에서 먼지가 있는곳을 찾고 움직이기
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(grid[i][j] != -1 && grid[i][j] != 0) { //현 시점에서 미세먼지가 있는지
                    int curr = grid[i][j]; //현재 칸의 미세먼지 수
                    int unit = curr / 5; // 확산되는 미세먼지 단위
                    int cnt = 0; //확산된 칸의 수
                    for(int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];

                        if(inRange(nx, ny) && grid[nx][ny] != -1) { //상, 하, 좌, 우가 영역 안에 있고, 공기청정기가 없는지
                            cnt += 1; //현 시점 기준 상,하,좌,우칸에 공기가 확산될 수 있는 수
                            temp[nx][ny] += unit;
                        }
                    }
                    deque.offerLast(new int[]{i, j, cnt, unit});
                }
            }
        }

        //원본 배열에서 미세먼지가 있던 칸의 나머지 먼지를 더하기
        while(!deque.isEmpty()) {
            int[] curr = deque.pollFirst();

            int x = curr[0];
            int y = curr[1];
            int cnt = curr[2];
            int unit = curr[3];

            int rest = grid[x][y] - (unit * cnt);
            temp[x][y] += rest;
        }

        grid = temp; //얕은복사
    }

    private static void circulateUpper() {
        int prev1 = grid[up][C - 1];
        //왼 -> 오
        for(int i = C - 1; i > 1; i--) {
            grid[up][i] = grid[up][i - 1];
        }
        grid[up][1] = 0; //무조건 0

        int prev2 = grid[0][C - 1];
        //아래 -> 위
        for(int i = 0; i < up - 1; i++) {
            grid[i][C - 1] = grid[i + 1][C - 1];
        }
        grid[up - 1][C - 1] = prev1;

        int prev3 = grid[0][0];
        //오 -> 왼
        for(int i = 0; i < C - 2; i++) {
            grid[0][i] = grid[0][i + 1];
        }
        grid[0][C - 2] = prev2;

        //위 -> 아래
        int prev4 = grid[up][0];
        for(int i = up; i > 1; i--) {
            grid[i][0] = grid[i - 1][0];
        }
        grid[1][0] = prev3;
        grid[up][0] = -1;
    }

    private static void circulateLower() {
        int prev1 = grid[down][C - 1];
        //오 -> 왼
        for(int i = C - 1; i > 1; i--) {
            grid[down][i] = grid[down][i - 1];
        }
        grid[down][1] = 0;

        int prev2 = grid[R - 1][C - 1];
        //위 -> 아래
        for(int i = R - 1; i > down + 1; i--) {
            grid[i][C - 1] = grid[i - 1][C - 1];
        }
        grid[down + 1][C - 1] = prev1;

        int prev3 = grid[R - 1][0];
        //오 -> 왼
        for(int i = 0; i < C - 2; i++) {
            grid[R - 1][i] = grid[R - 1][i + 1];
        }
        grid[R - 1][C - 2] = prev2;

        int prev4 = grid[down][0];
        //아래 -> 위
        for(int i = down; i < R - 2; i++) {
            grid[i][0] = grid[i + 1][0];
        }
        grid[R - 2][0] = prev3;
        grid[down][0] = -1;
    }



    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        grid = new int[R][C];

        List<Integer> fan = new ArrayList<>(); //공기청정기의 위치를 알기 위한 배열

        //방안의 미세먼지, 공기청정기 위치 입력
        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
                if(grid[i][j] == -1) fan.add(i);
            }
        }

        up = fan.get(0); //공기청정기 위쪽
        down = fan.get(1); //공기청정기 아래쪽

        for(int i = 0; i < T; i++) {
            spread();
            circulateUpper();
            circulateLower();
        }

        int result = checkDust();

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
}