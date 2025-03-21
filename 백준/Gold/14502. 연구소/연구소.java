
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static class Pair {
		int x;
		int y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static int N;
	static int M;
	static int[][] arr;
	static ArrayList<Pair> virus;
	static int safeArea;
	static int result;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		virus = new ArrayList<>();
		arr = new int[N][M];
		safeArea = 0;
		result = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 2) {
					virus.add(new Pair(i, j));
				} else if (arr[i][j] == 0) {
					safeArea++;
				}
			}
		}
		func();

		System.out.println(result);
	}

	static void func() {
		for (int a = 0; a < N * M - 2; a++) {
			if (arr[a / M][a % M] != 0)
				continue;
			for (int b = a + 1; b < N * M - 1; b++) {
				if (arr[b / M][b % M] != 0)
					continue;
				for (int c = b + 1; c < N * M; c++) {
					if (arr[c / M][c % M] != 0)
						continue;

					arr[a / M][a % M] = arr[b / M][b % M] = arr[c / M][c % M] = 1;
					result = Math.max(result, bfs_virus());
					arr[a / M][a % M] = arr[b / M][b % M] = arr[c / M][c % M] = 0;

				}
			}
		}
	}

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int bfs_virus() {
		Deque<Pair> q = new ArrayDeque<>();
		boolean[][] isVisited = new boolean[N][M];
		int cnt = 0;

		for (Pair p : virus) {
			q.offer(p);
			isVisited[p.x][p.y] = true;
		}

		while (!q.isEmpty()) {
			Pair curr = q.poll();

			if (arr[curr.x][curr.y] == 0) {
				cnt++;
			}

			for (int i = 0; i < 4; i++) {
				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M || isVisited[nx][ny] || arr[nx][ny] == 1)
					continue;
				isVisited[nx][ny] = true;
				q.offer(new Pair(nx, ny));
			}
		}
		return safeArea - cnt - 3; // 기둥 세운 곳도 지워졌으니 -3
	}
}