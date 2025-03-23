import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		int[][][] arr = new int[H][N][M];
		boolean[][][] isVisited = new boolean[H][N][M];

		Deque<Position> q = new ArrayDeque<>();

		for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					arr[h][i][j] = Integer.parseInt(st.nextToken());
					if (arr[h][i][j] == 1) {
						q.offer(new Position(h, i, j, 0));
						isVisited[h][i][j] = true;
					}
				}
			}
		}

		int[] dh = { -1, 0, 1 };
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		int day = Integer.MAX_VALUE;

		while (!q.isEmpty()) {
			Position curr = q.poll();

			for (int i = 0; i < 4; i++) {

				int nx = curr.x + dx[i];
				int ny = curr.y + dy[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M || isVisited[curr.h][nx][ny] || arr[curr.h][nx][ny] != 0) {
					continue;
				}
				isVisited[curr.h][nx][ny] = true;
				arr[curr.h][nx][ny] = curr.day + 1;
				q.offer(new Position(curr.h, nx, ny, curr.day + 1));
			}
			for (int h = 0; h < 3; h++) {
				int nh = curr.h + dh[h];
				if (nh < 0 || nh >= H || isVisited[nh][curr.x][curr.y] || arr[nh][curr.x][curr.y] != 0) {
					continue;
				}
				isVisited[nh][curr.x][curr.y] = true;
				arr[nh][curr.x][curr.y] = curr.day + 1;
				q.offer(new Position(nh, curr.x, curr.y, curr.day + 1));
			}

			if (q.isEmpty()) {
				day = curr.day;
			}
		}
		for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (arr[h][i][j] == 0) {
						day = -1;
					}
				}
			}
		}

		System.out.println(day);
	}

	static class Position {
		int x;
		int y;
		int h;
		int day;

		Position(int h, int x, int y, int day) {
			this.x = x;
			this.y = y;
			this.h = h;
			this.day = day;
		}

		@Override
		public String toString() {
			return "Position [x=" + x + ", y=" + y + ", h=" + h + ", day=" + day + "]";
		}

	}
}
