package SUMIN.week10;
import java.util.*;
public class Q43164 {
    class Solution {
        static ArrayList<String> list = new ArrayList<>();
        static boolean[] visited;
        public String[] solution(String[][] tickets) {
            String[] answer = {};

            visited = new boolean[tickets.length];
            dfs(0, "ICN", "ICN", tickets);
            Collections.sort(list);


            return list.get(0).split(" ");
        }

        static void dfs(int depth, String now, String path, String[][] tickets) {
            if (depth == tickets.length) {
                list.add(path);
                return;
            }
            for (int i = 0; i < visited.length; i++) {
                if (!visited[i] && now.equals(tickets[i][0])) {
                    visited[i] = true;
                    dfs(depth+1, tickets[i][1], path + " " +tickets[i][1], tickets);
                    visited[i] = false;
                }
            }
        }
    }
}
