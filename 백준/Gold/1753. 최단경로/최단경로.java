import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int V;
	static int E;
	static int startNode;

	static ArrayList<EdgeInfo>[] graph;
	static int[] distance;

	static class EdgeInfo {
		int startAt;
		int endAt;
		int cost;

		EdgeInfo(int startAt, int endAt, int cost) {
			this.startAt = startAt;
			this.endAt = endAt;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		startNode = Integer.parseInt(br.readLine());

		graph = new ArrayList[V + 1];
		for (int i = 0; i < V + 1; i++) {
			graph[i] = new ArrayList<>();
		}
		distance = new int[V + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());

			graph[start].add(new EdgeInfo(start, end, weight));
		}

		solve(startNode);

		for (int i = 1; i < V + 1; i++) {
			if (distance[i] == Integer.MAX_VALUE) {
				System.out.println("INF");
				continue;
			}
			System.out.println(distance[i]);
		}
	}

	static void solve(int node) {
		PriorityQueue<EdgeInfo> pq = new PriorityQueue<>(Comparator.comparing(e -> e.cost));
		for (EdgeInfo e : graph[node])
			pq.offer(e);
		distance[node] = 0;

		while (!pq.isEmpty()) {
			EdgeInfo e = pq.poll();
			int currS = e.startAt;
			int currE = e.endAt;
			int currCost = e.cost;

			if (distance[currE] > currCost) {
				distance[currE] = currCost;
			} else
				continue;

			for (EdgeInfo edge : graph[currE]) {
				pq.offer(new EdgeInfo(edge.startAt, edge.endAt, edge.cost + currCost));
			}
		}

	}

}