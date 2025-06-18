import java.io.*;
import java.util.*;

public class HJ3005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] grid = new char[R][C];
        for(int row = 0; row < R; row++) {
            String str = br.readLine();
            for(int col = 0; col < C; col++) {
                grid[row][col] = str.charAt(col);
            }
        }

        PriorityQueue<String> answers = new PriorityQueue<>();

        //열 단위 입력
        for(int i = 0; i < R; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < C; j++) {
                if(grid[i][j] != '#') {
                    sb.append(grid[i][j]);
                } else {
                    if(sb.length() >= 2) {
                        answers.offer(sb.toString());
                    }
                    sb.setLength(0);
                }
            }
            if(sb.length() >= 2) answers.offer(sb.toString());
        }

        //행 단위 입력
        for(int j = 0; j < C; j++) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < R; i++) {
                if(grid[i][j] != '#') {
                    sb.append(grid[i][j]);
                } else {
                    if(sb.length() >= 2) {
                        answers.offer(sb.toString());
                    }
                    sb.setLength(0);
                }
            }
            if(sb.length() >= 2) answers.offer(sb.toString());
        }

        bw.write(answers.peek());
        bw.flush();
        bw.close();
        br.close();
    }
}
