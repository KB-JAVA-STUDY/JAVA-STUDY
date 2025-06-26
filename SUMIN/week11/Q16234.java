package SUMIN.week11;

import java.io.*;
import java.util.*;

public class Q16234 {
    static int N, L, R;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean[][] visited;
    static int[][] population;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        population = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                population[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0; //인구 이동 발생 횟수
        while(true) {
            visited = new boolean[N][N];
            boolean moved = false; //이번 턴에 인구 이동했는지

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        if (bfs(i, j)) { // 연합이 2개 이상이면 인구 이동 발생
                            moved = true;
                        }
                    }
                }
            }
            if (!moved) break;
            day++;
        }
        System.out.println(day);

//        Deque<int[]> q = new ArrayDeque<>();
//        visited = new boolean[N][N];
//        visited[0][0] = true;
//        q.add(new int[] {0, 0, 1});
//        bfs(q);

    }

    static boolean bfs(int r, int c ) {
        Queue<int[]> q = new ArrayDeque<>();
        List<int[]> union = new ArrayList<>();
        q.add(new int[]{r, c});
        union.add(new int[]{r, c});
        visited[r][c] = true;

        int total = population[r][c]; //연합 총 인구
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0];
            int curC = cur[1];

            for (int d = 0; d < 4; d++) {
                int nr = curR + dr[d];
                int nc = curC + dc[d];

                if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
                    int diff = Math.abs(population[curR][curC] - population[nr][nc]);
                    if (diff >= L && diff <= R) {
                        q.add(new int[]{nr, nc});
                        union.add(new int[]{nr, nc});
                        visited[nr][nc] = true;
                        total += population[nr][nc];
                    }
                }
            }
        }
        if (union.size() == 1) return false; // 연합이 혼자면 이동 없음

        int avg = total / union.size(); // 인구 평균
        for (int[] pos : union) {
            population[pos[0]][pos[1]] = avg; // 인구 재배치
        }

        return true;
    }

//    static void bfs(Deque<int[]> q) {
//        int total = 0;
//
//        while(!q.isEmpty()) {
//            int[] cur = q.poll();
//            int r = cur[0], c = cur[1], dist = cur[2];
//
//            for (int i = 0; i < 4; i++) {
//                int nextR = r + dr[i], nextC = c + dc[i];
//                if (nextR >= 0 && nextR < N && nextC >= 0 && nextC < N) {
//                    if (!visited[nextR][nextC]) {
//                        int diff = population[r][c] - population[nextR][nextC];
//                        if (Math.abs(diff) >= L && Math.abs(diff) <= R) {
//                            q.add(new int[]{nextR, nextC, dist+1});
//                            visited[nextR][nextC] = true;
//                            total += population[nextR][nextC];
//                        }
//                    }
//                }
//            }
//        }
//    }



}
