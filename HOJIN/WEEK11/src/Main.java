import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] grid = new char[R][C];
        for(int i = 0; i < R; i++) {
            String str = br.readLine();
            for(int j = 0; j < C; j++) {
                grid[i][j] = str.charAt(j);
            }
        }

        PriorityQueue<String> words = new PriorityQueue<>();

        for(int i = 0; i < R; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < C; j++) {

            }
        }

    }

}