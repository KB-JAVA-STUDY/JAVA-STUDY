package BOSEONG.WEEK11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BS_3005 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] board = new char[R][C];
        for (int i = 0; i < R; i++) {
            board[i] = br.readLine().toCharArray();
        }

        List<String> words = new ArrayList<>();

        // 하나씩 해서 words에 저장할거임
        // 가로 방향
        for (int i = 0; i < R; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < C; j++) {
                if (board[i][j] != '#') {
                    sb.append(board[i][j]);
                } else {
                    if (sb.length() >= 2) {
                        words.add(sb.toString());
                    }
                    sb.setLength(0);
                }
            }
            if (sb.length() >= 2) {
                words.add(sb.toString());
            }
        }

        // 세로 방향
        for (int j = 0; j < C; j++) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < R; i++) {
                if (board[i][j] != '#') {
                    sb.append(board[i][j]);
                } else {
                    if (sb.length() >= 2) {
                        words.add(sb.toString());
                    }
                    sb.setLength(0);
                }
            }
            if (sb.length() >= 2) {
                words.add(sb.toString());
            }
        }

        Collections.sort(words);
        System.out.println(words.get(0));
    }
}
