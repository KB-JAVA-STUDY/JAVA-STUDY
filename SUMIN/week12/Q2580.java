package SUMIN.week12;

import java.util.*;
import java.io.*;
public class Q2580 {
    static int[][] quiz = new int[9][9];
    static boolean[] check = new boolean[9];
    static

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i < 9; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                quiz[i][j] = Integer.parseInt(st.nextToken());
            }
        }

    }

    static void rowCheck() {

    }

    static void colCheck() {

    }

    static boolean checkZero() {
        for (int i = 0; i < 9; i++) {
            //0의 개수가 1개인지 확인...
        }
    }
}
