import java.io.*;
import java.util.*;

public class Main {
    static boolean[] isVisited;
    static ArrayList<Edge>[] tree;
    static int result;
    static int farNode;

    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        if (n == 1) { 
            System.out.println(0);
            return;
        }
        isVisited = new boolean[n + 1];
        tree = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            tree[a].add(new Edge(b, c));
            tree[b].add(new Edge(a, c));
        }

        Arrays.fill(isVisited, false);
        dfs(1, 0);

        Arrays.fill(isVisited, false);
        result = 0;
        dfs(farNode, 0);

        System.out.println(result);
    }

    static void dfs(int node, int cost) {
        isVisited[node] = true;

        if (cost > result) {
            result = cost;
            farNode = node;
        }

        for (Edge e : tree[node]) {
            if (!isVisited[e.to]) {
                dfs(e.to, cost + e.weight);
            }
        }
    }
}
