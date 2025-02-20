import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Pair {
		int dest, cost;

		Pair(int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine()); // 도시 개수
		int M = Integer.parseInt(br.readLine()); // 버스 개수

		// 그래프 초기화 (인접 리스트)
		ArrayList<ArrayList<Pair>> graph = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}

		// 그래프 입력
		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); // 출발 도시
			int b = Integer.parseInt(st.nextToken()); // 도착 도시
			int cost = Integer.parseInt(st.nextToken()); // 비용
			graph.get(a).add(new Pair(b, cost));
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		int startAt = Integer.parseInt(st.nextToken()); // 출발 도시
		int endAt = Integer.parseInt(st.nextToken()); // 도착 도시

		// 거리 배열 초기화
		int[] distance = new int[N + 1];
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[startAt] = 0;

		// 다익스트라 알고리즘 (우선순위 큐 사용)
		PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.cost));
		pq.offer(new Pair(startAt, 0));

		while (!pq.isEmpty()) {
			Pair current = pq.poll();
			int currDest = current.dest;
			int currCost = current.cost;

			if (distance[currDest] < currCost)
				continue; // 이미 최적 거리면 패스

			// 인접 도시 탐색
			for (Pair next : graph.get(currDest)) {
				int nextDest = next.dest;
				int newCost = currCost + next.cost;

				if (newCost < distance[nextDest]) {
					distance[nextDest] = newCost; // 최단 거리 갱신
					pq.offer(new Pair(nextDest, newCost)); // 갱신된 노드 추가
				}
			}
		}

		// 결과 출력: 최소 비용
		System.out.println(distance[endAt]);
	}
}