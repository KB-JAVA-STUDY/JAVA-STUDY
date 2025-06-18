import java.io.*;
import java.util.*;

public class HJ16234 {

    private static class Country{
        int x, y;
        public Country(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int N, L, R;
    private static int[][] grid;
    private static boolean[][] visited;

    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    private static boolean allSame() {
        int cnt = grid[0][0];
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(cnt != grid[i][j]) return false;
            }
        }
        return true;
    }

    private static boolean canMove() {
        boolean[][] visited = new boolean[N][N];
        Deque<Country> deque = new ArrayDeque<>();
        visited[0][0] = true;
        deque.offerLast(new Country(0, 0));

        while(!deque.isEmpty()) {
            Country curr = deque.pollFirst();
            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];

                if(inRange(nx, ny)) {
                    int diff = Math.abs(grid[curr.x][curr.y] - grid[nx][ny]);
                    if(L <= diff && diff <= R) return true;
                    if(!visited[nx][ny]) {
                        deque.offerLast(new Country(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }
        }
        return false;
    }

    private static void bfs(int x, int y) {
        Country start = new Country(x, y);

        Deque<Country> deque = new ArrayDeque<>();
        deque.offerLast(start);
        visited[x][y] = true;

        Set<Country> set = new HashSet<>();
        set.add(start);
        int sum = grid[x][y];
        int cnt = 1;

        while(!deque.isEmpty()) {
            Country curr = deque.pollFirst();
            int currP = grid[curr.x][curr.y];
            for(int i = 0; i < 4; i++) {
                int nx = curr.x + dx[i];
                int ny = curr.y + dy[i];
                if(inRange(nx, ny)) {
                    int np = grid[nx][ny];
                    int diff = Math.abs(currP - np);

                    if(!visited[nx][ny] && (diff >= L && diff <= R)) {
                        visited[nx][ny] = true;
                        Country next = new Country(nx, ny);
                        set.add(next);
                        deque.offerLast(next);
                        sum += np;
                        cnt += 1;
                    }
                }
            }
        }

        int newP = (int)Math.floor((double)sum / cnt);
        for(Country c : set) {
            grid[c.x][c.y] = newP;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        //배열 입력
        grid = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                grid[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int day = 0;
        while(canMove()) {
            visited = new boolean[N][N];

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(!visited[i][j]) {
                        bfs(i, j);
                    }
                }
            }

            visited = new boolean[N][N];
            day++;
        }

        bw.write(String.valueOf(day));
        bw.flush();
        bw.close();
        br.close();
    }
}
