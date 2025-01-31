import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
  static ArrayList<ArrayList<Integer>> graph;
  static boolean[] visited;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    int start = Integer.parseInt(st.nextToken());

    graph = new ArrayList<>();
    for (int i = 0; i <= N; i++) {
      graph.add(new ArrayList<>());
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      graph.get(a).add(b);
      graph.get(b).add(a);
    }

    for (ArrayList<Integer> adj : graph) {
      Collections.sort(adj);
    }

    visited = new boolean[N + 1];

    dfs(start);
    Arrays.fill(visited, false);
    System.out.println();
    bfs(start);
  }

  static void dfs(int node) {
    visited[node] = true;
    System.out.print(node + " ");

    for (int next : graph.get(node)) {
      if (!visited[next]) {
        dfs(next);
      }
    }
  }

  static void bfs(int start) {
    Deque<Integer> queue = new ArrayDeque<>();
    queue.add(start);
    visited[start] = true;

    while (!queue.isEmpty()) {
      int node = queue.poll();
      System.out.print(node + " ");

      for (int next : graph.get(node)) {
        if (!visited[next]) {
          queue.add(next);
          visited[next] = true;
        }
      }
    }
  }
}
