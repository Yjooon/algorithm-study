import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] baseMap;
	static int[][] distance;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int testCase = 1;
		while (N != 0) {
			result = 0;
			baseMap = new int[N][N];
			distance = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					baseMap[i][j] = Integer.parseInt(st.nextToken());
					distance[i][j] = Integer.MAX_VALUE;
				}
			}

			solve(0, 0, baseMap[0][0]);

			System.out.println("Problem " + testCase + ": " + distance[N - 1][N - 1]);
			testCase++;
			N = Integer.parseInt(br.readLine());
		}
	}

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { 1, -1, 0, 0 };

	static class Pair {
		int x;
		int y;
		int cost;

		Pair(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}

	static void solve(int n, int m, int cost) {
		PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparing(p -> p.cost));
		pq.offer(new Pair(n, m, cost));

		while (!pq.isEmpty()) {
			Pair p = pq.poll();
			int currX = p.x;
			int currY = p.y;
			int currCost = p.cost;

			if (distance[currX][currY] > currCost) {
				distance[currX][currY] = currCost;
			} else {
				continue;
			}

			for (int i = 0; i < 4; i++) {
				int nX = currX + dx[i];
				int nY = currY + dy[i];
				if (nX < 0 || nY < 0 || nX >= N || nY >= N)
					continue;
				pq.offer(new Pair(nX, nY, currCost + baseMap[nX][nY]));
			}
		}
	}
}