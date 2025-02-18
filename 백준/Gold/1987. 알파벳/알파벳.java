import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
	static int R;
	static int C;

	static int[][] baseMap;
	static Deque<Pair> q;
	static int[] arr;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static int result = 0;

	static class Pair {
		int x;
		int y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static void dfs(Pair p) {
		arr[baseMap[p.x][p.y]]++;

		for (int i = 0; i < 4; i++) {
			int nextX = p.x + dx[i];
			int nextY = p.y + dy[i];

			if (nextX < 0 || nextY < 0 || nextX >= R || nextY >= C)
				continue;
			if (arr[baseMap[nextX][nextY]] == 1)
				continue;

			dfs(new Pair(nextX, nextY));
			result = Math.max(result, arrSum());
			arr[baseMap[nextX][nextY]]--;
		}
	}

	private static int arrSum() {
		int temp = 0;
		for (int i = 0; i < 26; i++) {
			temp += arr[i];
		}
		return temp;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		baseMap = new int[R][C];

		for (int i = 0; i < R; i++) {
			String s = br.readLine();
			for (int j = 0; j < C; j++) {
				baseMap[i][j] = s.charAt(j) - 'A';
			}
		}

		arr = new int[26];
		q = new ArrayDeque<>();

		dfs(new Pair(0, 0));

		result = Math.max(result, arrSum());

		System.out.println(result);

	}
}
