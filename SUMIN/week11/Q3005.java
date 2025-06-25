package SUMIN.week11;

import java.util.*;
import java.io.*;
public class Q3005 {
    static PriorityQueue<String> pq = new PriorityQueue<>();
    static int R;
    static int C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        String[][] arr = new String[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            String str = st.nextToken();
            for (int j = 0; j < C; j++) {
                arr[i][j] = str.substring(j, j+1);
            }
        }

//        for (String[] arr2 : arr) {
//            System.out.println(Arrays.toString(arr2));
//        }


        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                // 가로 방향 단어 탐색
                if ((j == 0 || arr[i][j - 1].equals("#")) && !arr[i][j].equals("#")) {
                    FindWordRight(arr, i, j);
                }
                // 세로 방향 단어 탐색
                if ((i == 0 || arr[i - 1][j].equals("#")) && !arr[i][j].equals("#")) {
                    FindWordBot(arr, i, j);
                }
            }
        }
        System.out.println(pq.peek());
    }

    // 오른쪽으로 쭉 가서
    // priority queue에 집어넣기
    static void FindWordRight(String[][] arr, int x, int y) {
        String str = arr[x][y];
        while (y + 1 < C) {
            if (!arr[x][y + 1].equals("#")) {
                str += arr[x][y + 1];
                y++;
            } else break;
        }

        if (str.length() >= 2) {
            pq.add(str);
        }

    }
    //아래로 쭉가서 pq에 집어넣기
    static void FindWordBot(String[][] arr, int x, int y) {
        String str = arr[x][y];
        while (x + 1 < R) {
            if (!arr[x + 1][y].equals("#")) {
                str += arr[x + 1][y];
                x++;
            } else break;
        }
        if (str.length() >= 2) {
            pq.add(str);
        }
    }
}
