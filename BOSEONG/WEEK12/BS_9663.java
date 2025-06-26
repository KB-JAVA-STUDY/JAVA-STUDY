package BOSEONG.WEEK12;

import java.util.Scanner;

public class BS_9663 {
    static int N;
    static int[] board;
    static int count;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        board = new int[N];  // board[i]는 i번째 행에서 퀸의 위치 (열 번호)
        count = 0;
        
        placeQueen(0);
        System.out.println(count);
    }

    //각 한 행마다 하나씩 둬야함
    public static void placeQueen(int row) {
        // 행 끝에 도착하게 되면 이제 ++
        if (row == N) {
            count++;
            return;
        }
        // 여기서 이제 비교하는 거임 N까지해서 borad[row] = col 넣어서
        // row 행 col 열에 있으니까 확인
        for (int col = 0; col < N; col++) {
            board[row] = col;
            if (isValid(row)) {
                placeQueen(row + 1);
            }
        }
    }

    // 여기서 확인 같은 열에 있는지 확인
    // 그 다음 대각선도 안되니까 확인
    public static boolean isValid(int row) {
        for (int i = 0; i < row; i++) {
            if (board[i] == board[row] ||  // 같은 열 검사
                Math.abs(board[row] - board[i]) == row - i) {  // 대각선 검사
                return false;
            }
        }
        return true;
    }
}
