package SUMIN.week12;

import java.util.*;
import java.io.*;

public class Q1941 {
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean[] visited;
    static int[] selected = new int[7];
    static String[][] arr;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new String[5][5];


        for (int i = 0; i < 5; i++) {
            String input = br.readLine();
            for (int j = 0; j < 5; j++) {
                arr[i][j] = input.substring(j, j+1);
            }
        }

        backTracking(0, 0, 0);
        System.out.println(answer);

    }

    static void backTracking(int depth, int numY, int start) {
        if (numY >= 4) return;

        if (depth == 7) {
            visited = new boolean[7];
            BFS();
            return;
        }

        //백트래킹은 0 ~ 24지만, 배열은 5*5 2차원이다.
        //즉, 5로 나눈 몫을 행으로, 나머지를 열로 설정해주면 5*5 행렬을 표현 가능하다.
        for(int i = start ; i < 25 ; i++) {
            selected[depth] = i;
            if(arr[i/5][i%5].equals("Y")) {
                backTracking(depth+1, numY+1, i+1);
            }
            else {
                backTracking(depth+1, numY, i+1);
            }

        }
    }

    static void BFS() {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{selected[0]/5, selected[0]%5}); // 가장 처음 selected[0] 넣기
        visited[0] = true;
        int count = 1; // 몇 명이 연결되어있는지 확인


        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int curR = cur[0], curC = cur[1];

            for (int i = 0; i < 4; i++) {
                int nextR = curR + dr[i], nextC = curC + dc[i];

                if(!isValid(nextR, nextC)) continue;

                for (int j = 0; j < 7; j++) {
                    if (!visited[j] && selected[j] == nextR*5 + nextC) {
                        q.add(new int[]{nextR, nextC});
                        visited[j] = true;
                        count++;
                    }
                }
            }
        }
        if (count == 7) {
            answer++;
        }
    }

    static boolean isValid(int r, int c) {
        if (r >= 0 && r < 5 && c >= 0 && c < 5) return true;
        return false;
    }

}

