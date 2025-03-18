import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;

public class Main {
	static int[][] arr;
	static int[][] isVisited; // 1: 바깥 공기 / 2: 방문한 치즈 / -1: 녹을 치즈 / 0: 방문안함 / -2: 내부 공기

	static Deque<Pair> meltingQ;
	static List<Pair> cheeseQ;

	static int N;
	static int M;

	static int dx[] = { -1, 1, 0, 0 };
	static int dy[] = { 0, 0, -1, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		meltingQ = new ArrayDeque<>();
		cheeseQ = new ArrayList<>();

		arr = new int[N][M];
		isVisited = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1)
					cheeseQ.add(new Pair(i, j));
			}
		}

		bfs_airCheck(0, 0);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (isVisited[i][j] == 0 && arr[i][j] == 0) {
					isVisited[i][j] = -2;
				}
			}
		}

		int day = 0;
		while (!cheeseQ.isEmpty()) {
			day++;

			cheeseCheck();
			while (!meltingQ.isEmpty()) {
				Pair curr = meltingQ.poll();
				isVisited[curr.x][curr.y] = 1;
				arr[curr.x][curr.y] = 0;

				for (int i = 0; i < 4; i++) {
					int nx = curr.x + dx[i];
					int ny = curr.y + dy[i];

					if (nx < 0 || ny < 0 || nx >= N || ny >= M || arr[nx][ny] == 1) {
						continue;
					}
					if (isVisited[nx][ny] == -2) {
						bfs_internalAirCheck(nx, ny);
					}
				}
			}
		}
		System.out.println(day);
	}

	static void bfs_internalAirCheck(int x, int y) {
		Deque<Pair> q = new ArrayDeque<>();
		q.offer(new Pair(x, y));

		while (!q.isEmpty()) {
			Pair curr = q.poll();
			int cx = curr.x;
			int cy = curr.y;

			isVisited[cx][cy] = 1;

			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M || arr[nx][ny] != 0) {
					continue;
				}
				if (isVisited[nx][ny] == -2) {
					isVisited[nx][ny] = 1;
					q.offer(new Pair(nx, ny));
				}	
			}
		}
	}

	static void cheeseCheck() {
		for (int i = 0; i < cheeseQ.size(); i++) {
			int cx = cheeseQ.get(i).x;
			int cy = cheeseQ.get(i).y;

			isVisited[cx][cy] = 2;
			if (isMelting(cx, cy)) {
				cheeseQ.remove(i);
				i--;
				isVisited[cx][cy] = -1;
				meltingQ.offer(new Pair(cx, cy));
			}
		}
	}

	static class Pair {
		int x;
		int y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			return x == other.x && y == other.y;
		}
	}

	// 외부 공기 체크
	static void bfs_airCheck(int x, int y) {
		Deque<Pair> q = new ArrayDeque<>();
		q.offer(new Pair(x, y));

		while (!q.isEmpty()) {
			Pair curr = q.poll();
			int cx = curr.x;
			int cy = curr.y;

			isVisited[cx][cy] = 1;

			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= M || arr[nx][ny] != 0 || isVisited[nx][ny] == 1) {
					continue;
				}
				isVisited[nx][ny] = 1;
				q.offer(new Pair(nx, ny));
			}
		}
	}

	static boolean isMelting(int x, int y) {
		int airCnt = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (isVisited[nx][ny] == 1)
				airCnt++;
			if (airCnt >= 2) {
				return true;
			}
		}

		return false;
	}
}
