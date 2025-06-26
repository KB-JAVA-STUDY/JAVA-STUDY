package BOSEONG.WEEK11;

import java.io.*;
import java.util.*;

public class BS_16234 {
    static int N, L, R;
    static int[][] arr;
    static boolean[][] v;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};
    static ArrayList<int[]> list;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        arr= new int[N][N];

        // 여기까지 arr 생성
        for(int i =0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        

        System.out.println(move());
    }

    static int move(){
        int result = 0;
        
        while(true){
            boolean isMove = false;
            v = new boolean[N][N];
            for(int i = 0; i< N; i++){
                for (int j = 0; j < N; j++){
                    if(!v[i][j]){
                        int sum = bfs(i,j);
                        if(list.size() > 1){
                            int avg = sum / list.size();

                            for(int n = 0; n < list.size(); n++){
                                int x = list.get(n)[0];
                                int y = list.get(n)[1];

                                arr[x][y] = avg;
                            }
                            isMove = true;
                        }
                    }
                }
            }
            if(!isMove) return result;

            result++;
        }
        
    }

    static int bfs(int x, int y){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x,y});

        list = new ArrayList<>();
        list.add(new int[]{x,y});

        v[x][y] = true;
        int sum = arr[x][y];
        while(!q.isEmpty()){
            int tmp[] = q.poll();
            for(int k = 0; k < 4; k++){
                int nx = tmp[0] + dx[k];
                int ny = tmp[1] + dy[k];
                if(0 <= nx && nx < N && 0 <= ny && ny < N){
                    if(!v[nx][ny] &&L <= Math.abs(arr[nx][ny] - arr[tmp[0]][tmp[1]]) && Math.abs(arr[nx][ny] - arr[tmp[0]][tmp[1]]) <= R){
                        v[nx][ny] = true;
                        q.add(new int[]{nx,ny});
                        list.add(new int[]{nx,ny});
                        sum += arr[nx][ny];
                    }
                }
            }
        }
        return sum;
    }
}
