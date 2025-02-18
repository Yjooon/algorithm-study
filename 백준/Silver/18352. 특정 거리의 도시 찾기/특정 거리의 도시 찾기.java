import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K, X;
	static ArrayList<ArrayList<Integer>> graph;
	static int[] distance;
	static final int INF = Integer.MAX_VALUE;

	public static void dijkstra(int start) {
		PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
		pq.offer(new int[] { 0, start }); // (거리, 노드번호)
		distance[start] = 0;

		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			int dist = curr[0];
			int node = curr[1];

			if (distance[node] < dist)
				continue; // 이미 처리된 노드는 스킵

			for (int next : graph.get(node)) {
				int cost = dist + 1; // 모든 간선의 가중치는 1
				if (cost < distance[next]) {
					distance[next] = cost;
					pq.offer(new int[] { cost, next });
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		distance = new int[N + 1];
		Arrays.fill(distance, INF);

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
		}

		dijkstra(X);

		boolean found = false;
		for (int i = 1; i <= N; i++) {
			if (distance[i] == K) {
				System.out.println(i);
				found = true;
			}
		}

		if (!found) {
			System.out.println(-1);
		}
	}
}