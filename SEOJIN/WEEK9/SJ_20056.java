import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SJ_20056 {
    static class fireball{
        int r, c, m, s, d;
        public fireball(int r, int c, int m, int s, int d){
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};	//방향 r값 변경값
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};	//방향 c값 변경값
    static ArrayList<fireball>[][] map;	//파이어볼 이동했을 때 정보
    static ArrayList<fireball> fireballs = new ArrayList<>();	//모든 파이어볼 정보

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        map = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) -1;
            int c = Integer.parseInt(st.nextToken()) -1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            fireballs.add(new fireball(r, c, m, s, d));
        }

        //K번 이동명령 진행
        for (int i = 0; i < K; i++) {
            move(N);
            divide(N);
        }
        bw.write(fireCal() + "");	//메테오 질량의 합 BufferedWriter 저장
        bw.flush();		//결과 출력
        bw.close();
        br.close();



    }

    /*
    파이어볼들을 전부 이동시켜서 이동한 위치에 저장하는 함수
    격자의 크기는 N × N
    파이어볼들은 방향과 속도를 가지고 이동
    격자의 경계를 넘어가면 반대편으로 나가게 하는 "원형 격자" 처리
     */

    static void move(int N){

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j].clear();  // 이전 턴의 이동 정보 초기화
            }
        }

        for (fireball cur : fireballs){
            int tempR = (cur.r + N + dr[cur.d] * (cur.s % N)) % N;
            int tempC = (cur.c + N + dc[cur.d] * (cur.s % N)) % N;
            cur.r = tempR;   // 행 위치
            cur.c = tempC;   // 열 위치


            map[cur.r][cur.c].add(cur);
        }
    }

    static void divide(int N){
        for(int r = 0; r<N;r++){
            for(int c = 0; c<N;c++) {
                //파이어볼 개수가 2개 미만일 때
                if(map[r][c].size() < 2){
                    map[r][c].clear();
                    continue;
                }
                // map[r][c] 칸에 파이어볼 2개 이상일 때
                int mSum = 0, sSum = 0, oddCount = 0, evenCount = 0;
                int size = map[r][c].size();
                List<fireball> toRemove = new ArrayList<>();
                //분열 진행!
                for(fireball cur : map[r][c]){
                    mSum += cur.m;	//질량 더하기
                    sSum += cur.s;	//속도 더하기
                    if(cur.d % 2 == 1)	//방향 홀수일 때
                        oddCount++;
                    else		//방향 짝수일 때
                        evenCount++;
                    toRemove.add(cur);	//분열될 파이어볼 제거
                }

                fireballs.removeAll(toRemove);  //  실제 전체 파이어볼 목록에서 제거해야 함
                map[r][c].clear();
                mSum /= 5;	//분열될 질량 구하기
                if(mSum == 0)	//분열될 질량이 0이면 분열 실패!
                    continue;
                sSum /= size;	//분열될 속도 구하기
                //모든 파이어볼 방향이 짝수이거나 홀수일 때
                if(oddCount == size || evenCount == size){
                    for(int i=0;i<8;i+=2)	//{0, 2, 4, 6} 방향 분열
                        fireballs.add(new fireball(r,c,mSum, sSum, i));
                }else{
                    for(int i=1;i<8;i+=2)	//{1, 3, 5, 7} 방향 분열
                        fireballs.add(new fireball(r,c,mSum, sSum, i));
                }
            }
        }
    }
    //파이어볼 질량의 합 구하는 함수
    static int fireCal(){
        int result = 0;
        //모든 질량 더하기!
        for(fireball cur : fireballs)
            result += cur.m;
        return result;
    }

}
