import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static class Edge {
		int from;
		int to;
		int cost;

		Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		boolean minusCycle = false;

		ArrayList<Edge> graph = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			graph.add(new Edge(a, b, c));
		}

		long[] dist = new long[N + 1];
		Arrays.fill(dist, Long.MAX_VALUE);
		dist[1] = 0;

		for (int i = 1; i < N; i++) {
			for (Edge e : graph) {
				if (dist[e.from] != Long.MAX_VALUE && dist[e.from] + e.cost < dist[e.to])
					dist[e.to] = dist[e.from] + e.cost;
			}
		}
		for (Edge edge : graph) {
			if (dist[edge.from] != Long.MAX_VALUE && dist[edge.from] + edge.cost < dist[edge.to]) {
				minusCycle = true;
				break;
			}
		}

		if (minusCycle)
			System.out.println(-1);
		else {
			for (int i = 2; i <= N; i++) {
				System.out.println(dist[i] == Long.MAX_VALUE ? -1 : dist[i]);
			}
		}
	}
}