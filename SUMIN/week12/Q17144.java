package SUMIN.week12;

import java.io.*;
import java.util.*;

public class Q17144 {
    static int R;
    static int C;
    static int T;
    static int[][] arr; // 원래 값 저장할 배열
    static int[][] resultArr; //매 초 미세먼지 이동한 결과 저장할 배열
    static int air1 = -1;
    static int air2 = -1;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()); //[R x C]
        C = Integer.parseInt(st.nextToken()); //[R x C]
        T = Integer.parseInt(st.nextToken()); //시간

        arr = new int[R][C];
        resultArr = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == -1) {
                    if (air1 == -1) air1 = i;
                    else air2 = i;
                }
            }
        }

        //깊은복사
        ArrayDeepCopy(arr, resultArr);

        for (int i = 0; i < T; i++) {
            SpreadDust();
//            AirPurifier();
            purify();
        }

        System.out.println(GetTotalDust(arr));
    }

    static void ArrayDeepCopy(int[][] arr1, int[][] arr2) { //arr1을 arr2로 복사
        for (int i = 0; i < arr1.length; i++) {
            System.arraycopy(arr1[i], 0, arr2[i], 0, arr1[i].length);
        }
    }

    static int GetTotalDust(int[][] arr) {
        int sum = 0;
        for (int[] row : arr) {
            for (int value : row) {
                if (value > 0) sum += value;
            }
        }
        return sum;
    }

//    static void SpreadDust() {
//        ArrayDeepCopy(resultArr, arr);
//        for (int i = 0; i < resultArr.length; i++) {
//            for (int j = 0; j < resultArr[0].length; j++) {
//                int target = arr[i][j] / 5;
//                for (int k = 0; k < 3; k++) {
//                    int count = 0;
//                    int nextR = i + dr[k];
//                    int nextC = j + dc[k];
//                    if (nextR >= 0 && nextC >= 0 && arr[nextR][nextC] != -1) {
//                        resultArr[i+dr[k]][j+dc[k]] = arr[i][j] / 5;
//                        count++;
//                    }
//                    resultArr[i][j] -= target*count;
//                }
//            }
//        }
//    }

    static void SpreadDust() {
        int[][] temp = new int[R][C];

        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (arr[r][c] > 0) {
                    int amount = arr[r][c] / 5;
                    int count = 0;
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        if (nr >= 0 && nr < R && nc >= 0 && nc < C && arr[nr][nc] != -1) {
                            temp[nr][nc] += amount;
                            count++;
                        }
                    }
                    temp[r][c] -= amount * count;
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                arr[i][j] += temp[i][j];
            }
        }
    }

    static void purify() {
        // 위쪽: 반시계 방향
        for (int i = air1 - 1; i > 0; i--) arr[i][0] = arr[i - 1][0];
        for (int j = 0; j < C - 1; j++) arr[0][j] = arr[0][j + 1];
        for (int i = 0; i < air1; i++) arr[i][C - 1] = arr[i + 1][C - 1];
        for (int j = C - 1; j > 1; j--) arr[air1][j] = arr[air1][j - 1];
        arr[air1][1] = 0;

        // 아래쪽: 시계 방향
        for (int i = air2 + 1; i < R - 1; i++) arr[i][0] = arr[i + 1][0];
        for (int j = 0; j < C - 1; j++) arr[R - 1][j] = arr[R - 1][j + 1];
        for (int i = R - 1; i > air2; i--) arr[i][C - 1] = arr[i - 1][C - 1];
        for (int j = C - 1; j > 1; j--) arr[air2][j] = arr[air2][j - 1];
        arr[air2][1] = 0;
    }

//    static void AirPurifier() {
//        boolean first = true;
//
//        for (int i = 0; i < R; i++) {
//            if (arr[i][0] == -1) {
//                if (first) {
//                    A1 = i; // 위쪽 공기청정기 행 : 반시계방향
//                    MoveArrayFirst();
//                    first = false;
//                } else {
//                    A2 = i; // 아래쪽 공기청정기 행 : 시계방향
//                    MoveArraySecond();
//                    break;
//                }
//            }
//        }
//    }
//
//    static void MoveArrayFirst() {
//        // 반시계 방향 위쪽 순환
//        // 왼쪽 → 위
//        for (int i = A1 - 1; i > 0; i--) {
//            arr[i][0] = arr[i - 1][0];
//        }
//        // 위 → 오른쪽
//        for (int j = 0; j < C - 1; j++) {
//            arr[0][j] = arr[0][j + 1];
//        }
//        // 오른쪽 → 아래
//        for (int i = 0; i < A1; i++) {
//            arr[i][C - 1] = arr[i + 1][C - 1];
//        }
//        // 아래 → 왼쪽
//        for (int j = C - 1; j > 1; j--) {
//            arr[A1][j] = arr[A1][j - 1];
//        }
//        arr[A1][1] = 0; // 공기 정화
//    }

//    static void MoveArraySecond() {
//        // 시계 방향 아래쪽 순환
//        // 왼쪽 → 아래
//        for (int i = A2 + 1; i < R - 1; i++) {
//            arr[i][0] = arr[i + 1][0];
//        }
//        // 아래 → 오른쪽
//        for (int j = 0; j < C - 1; j++) {
//            arr[R - 1][j] = arr[R - 1][j + 1];
//        }
//        // 오른쪽 → 위
//        for (int i = R - 1; i > A2; i--) {
//            arr[i][C - 1] = arr[i - 1][C - 1];
//        }
//        // 위 → 왼쪽
//        for (int j = C - 1; j > 1; j--) {
//            arr[A2][j] = arr[A2][j - 1];
//        }
//        arr[A2][1] = 0; // 공기 정화
//    }

}
