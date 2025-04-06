import java.util.*;

class Solution {
    static boolean[] isVisited;
    public int solution(int n, int[][] results) {
        int answer = 0;

        ArrayList<Integer>[] wins = new ArrayList[n + 1];
        ArrayList<Integer>[] loses = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            wins[i] = new ArrayList<>();
            loses[i] = new ArrayList<>();
        }

        for (int[] result : results) {
            wins[result[0]].add(result[1]);
            loses[result[1]].add(result[0]);
        }

        for (int i = 1; i <= n; i++) {
            isVisited = new boolean[n + 1];
            int winCount = dfs(i, wins);
            isVisited = new boolean[n + 1];
            int loseCount = dfs(i, loses);

            if (winCount + loseCount == n - 1) {
                answer++;
            }
        }

        return answer;
    }

    static int dfs(int start, ArrayList<Integer>[] graph) {
        int count = 0;
        for (int next : graph[start]) {
            if (!isVisited[next]) {
                isVisited[next] = true;
                count++;
                count += dfs(next, graph);
            }
        }
        return count;
    }
}
