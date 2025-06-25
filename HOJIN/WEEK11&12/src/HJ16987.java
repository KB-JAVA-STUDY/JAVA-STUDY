import java.io.*;
import java.util.*;

public class HJ16987{
    private static int N;
    private static int maxCnt = 0;
    private static Egg[] eggs;

    private static class Egg{
        int durability, weight;
        public Egg(int durability, int weight) {
            this.durability = durability;
            this.weight = weight;
        }

        public boolean isBroken() {
            return durability <= 0;
        }
    }

    private static void dfs(int idx) {
        //맨 오른쪽일 경우
        if(idx == N) {
            int cnt = 0;
            for(Egg egg : eggs) {
                if (egg.isBroken()) cnt++;
            }
            maxCnt = Math.max(maxCnt, cnt);
            return;
        }

        //현재 계란이 깨졌을경우
        if(eggs[idx].isBroken()) {
            dfs(idx + 1);
            return;
        }

        boolean hit = false;
        for(int i = 0; i < N; i++) {
            //현재 들고있는 계란 or 계란이 깨졌을경우 건너뛰기
            if (i == idx || eggs[i].isBroken()) continue;

            hit = true; //한번이라도 친 경우

            eggs[idx].durability -= eggs[i].weight;
            eggs[i].durability -= eggs[idx].weight;

            dfs(idx + 1);

            //백트랙킹
            eggs[idx].durability += eggs[i].weight;
            eggs[i].durability += eggs[idx].weight;
        }

        //현재 계란으로 칠 수 있는 계란이 없을 경우
        if(!hit) {
            dfs(idx + 1);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());

        eggs = new Egg[N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            eggs[i] = new Egg(d, w);
        }

        dfs(0);

        bw.write(String.valueOf(maxCnt));
        bw.flush();
        bw.close();
        br.close();


    }
}