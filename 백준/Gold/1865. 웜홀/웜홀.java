import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Edge {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}

	static int N, M, W;
	static List<Edge> edges;
	static final int INF = 1000000000;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int TC = Integer.parseInt(st.nextToken());
		StringBuilder sb = new StringBuilder();

		while (TC-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			edges = new ArrayList<>();

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());

				edges.add(new Edge(S, E, T));
				edges.add(new Edge(E, S, T));
			}

			for (int i = 0; i < W; i++) {
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());

				edges.add(new Edge(S, E, -T));
			}

			for (int i = 1; i <= N; i++) {
				edges.add(new Edge(0, i, 0));
			}

			if (bellmanFord()) {
				sb.append("YES\n");
			} else {
				sb.append("NO\n");
			}
		}
		System.out.print(sb);
	}

	static boolean bellmanFord() {
		int[] dist = new int[N + 1];
		Arrays.fill(dist, INF);
		dist[0] = 0;

		for (int i = 0; i < N; i++) {
			boolean updated = false;
			for (Edge e : edges) {
				if (dist[e.from] != INF && dist[e.to] > dist[e.from] + e.weight) {
					dist[e.to] = dist[e.from] + e.weight;
					updated = true;
				}
			}
			if (!updated)
				break;
		}

		for (Edge e : edges) {
			if (dist[e.from] != INF && dist[e.to] > dist[e.from] + e.weight) {
				return true;
			}
		}
		return false;
	}
}
