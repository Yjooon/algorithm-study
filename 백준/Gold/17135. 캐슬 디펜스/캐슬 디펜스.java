import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int D;

	static int[][] baseMap;
	static boolean[][] isVisited;

	static int result;

	static int archer1 = 0;
	static int archer2 = 0;
	static int archer3 = 0;

	static Set<Pair> targetList;

	static class Pair {
		int x;
		int y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		baseMap = new int[N][M];
		targetList = new HashSet<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				baseMap[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		rememberMap();
	}

	static int[][] tempMap;

	static void rememberMap() {
		tempMap = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tempMap[i][j] = baseMap[i][j];
			}
		}
	}

	static void recoverMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				baseMap[i][j] = tempMap[i][j];
			}
		}
	}

	static int killCount; // 이번 궁수 배치의 처치 수

	static void solve() {
		isVisited = new boolean[N][M];
		for (int a = 0; a < M; a++) {
			for (int b = a + 1; b < M; b++) {
				for (int c = b + 1; c < M; c++) {
					archer1 = a;
					archer2 = b;
					archer3 = c;
					killCount = 0;
					targetList = new HashSet<>();
					recoverMap();

					for (int i = 1; i <= N; i++) {
						shoot(i);
					}
					result = Math.max(result, killCount);
				}
			}
		}
	}

	static void shoot(int turn) {
		isVisited = new boolean[N][M];
		archerShoot(turn, archer1);
		isVisited = new boolean[N][M];
		archerShoot(turn, archer2);
		isVisited = new boolean[N][M];
		archerShoot(turn, archer3);

		for (Pair p : targetList) {
			if (p == null)
				continue;
			if (baseMap[p.x][p.y] == 1) {
				killCount++;
				baseMap[p.x][p.y] = 0;
			}
		}
	}

	static void archerShoot(int turn, int archerLoc) { // turn은 1턴부터 N턴까지 진행
		Deque<Pair> q = new ArrayDeque<Pair>();
		int[] dx = { 0, -1, 0 };
		int[] dy = { -1, 0, 1 };

		q.offerLast(new Pair(N - turn, archerLoc)); // 궁수 1 위치 좌표. 문제의 위치보다 한 칸 위에 있기 때문에 D를 조작했음. 턴이 지나면 N이 줄어듦.
		while (!q.isEmpty()) {
			Pair p = q.poll();
			int currX = p.x;
			int currY = p.y;

			if (Math.abs(currX - (N - turn)) + Math.abs(currY - archerLoc) > D - 1) // D-1을 통해 아쳐의 위치가 격자 위에서 진행하도록 가정.
				break;
			if (!isVisited[currX][currY])
				isVisited[currX][currY] = true;

			if (baseMap[currX][currY] == 1) {
				targetList.add(new Pair(currX, currY));
				break;
			}

			for (int i = 0; i < 3; i++) {
				int nx = currX + dx[i];
				int ny = currY + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				if (isVisited[nx][ny])
					continue;

				q.offerLast(new Pair(nx, ny));
				isVisited[nx][ny] = true;
			}
		}
		q.clear();
	}

	static void removeEnemy(int line) {
		for (int i = 0; i < M; i++) {
			baseMap[line][i] = 0;
		}
	}

	public static void main(String[] args) throws IOException {
		input();
		solve();
		System.out.println(result);
	}
}