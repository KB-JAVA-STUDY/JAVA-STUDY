package BOSEONG.WEEK12;

import java.io.*;
import java.util.*;

public class BS_17144 {
    static int R, C, T;
    static int[][] arr;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static boolean[][] v;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        arr = new int[R][C];

        // 여기까지 배열 채우기
        for(int i = 0; i < R; i ++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

       
        // 여기까지 배열 채우기
        // -1이 공기청정기 위치
        

        for(int t = 0; t < T; t++){
            // 확산 시키기
            extend();
            // 공기청소기 일하기
            clean();
        }


        // 여기서 확산 다하고 청소 다한 후 계산
        int answer = 0;
        for(int i = 0; i < R; i ++){
            for(int j = 0; j < C; j++){
                //answer += arr[i][j];
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(answer);
    }

    // 확산 시키기
    static void extend(){
        v = new boolean[R][C];
        int[][] copy = new int[R][C];

        // 여기서 0이상의 값 좌표들 저장
        // -1인 것은 미리 옮겨놓음
        List<int[]> list = new ArrayList<>();
        for(int i = 0 ; i < R; i++){
            for(int j = 0; j < C; j++){
                if(arr[i][j] > 0) list.add(new int[]{i,j});
                else if(arr[i][j] == -1) copy[i][j] = -1;
            }
        }

        // 이제 여기서 list안에 좌표만 꺼내서 상하좌우로 뿌려주기
        for(int j = 0; j < list.size(); j++){
            int x = list.get(j)[0];
            int y = list.get(j)[1];
            copy[x][y] += arr[x][y];
            for(int k = 0; k < 4; k++){
                int nx = x + dx[k];
                int ny = y + dy[k];

                if(0 <= nx && nx < R && 0 <= ny && ny < C && arr[nx][ny] != -1){
                    int num = arr[x][y] / 5;
                    copy[x][y] -= num;
                    copy[nx][ny] += num;
                }
            }
        }
        arr = copy;
    }

    static void clean(){
        List<int[]> xy = new ArrayList<>();

        // x1 ~ y2 저장
        for(int i = 0; i < R; i++){
            if(arr[i][0] == -1) xy.add(new int[]{i,0});
        }
        int x1, x2, y1, y2;
        x1 = xy.get(0)[0];
        y1 = xy.get(0)[1];
        x2 = xy.get(1)[0];
        y2 = xy.get(1)[1];

        // 위에 돌리기
        

        // 아래 돌리기

    }
}
