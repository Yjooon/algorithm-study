import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static int[][] maze;
	static boolean[][] isVisited;
	static Pair startLoc;
	static Pair endLoc;

	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };

	static boolean result;

	static class Pair {
		int x;
		int y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static void dfs(int x, int y) {
		if (isVisited[x][y] || result)
			return;
		if (maze[x][y] == 3) {
			result = true;
			return;
		}

		isVisited[x][y] = true;

		for (int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			if (nextX < 0 || nextY < 0 || nextX >= 16 || nextY >= 16)
				continue;
			if (maze[nextX][nextY] == 1)
				continue;

			dfs(nextX, nextY);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int test_case = 1; test_case <= 10; test_case++) {
			int t = Integer.parseInt(br.readLine());

			maze = new int[16][16];
			isVisited = new boolean[16][16];
			result = false;

			for (int i = 0; i < 16; i++) {
				String s = br.readLine();
				for (int j = 0; j < 16; j++) {
					maze[i][j] = s.charAt(j) - '0';
					if (maze[i][j] == 2)
						startLoc = new Pair(i, j);
					if (maze[i][j] == 3)
						endLoc = new Pair(i, j);
				}
			}

			dfs(startLoc.x, startLoc.y);

			System.out.println("#" + test_case + " " + (result ? 1 : 0));
		}
	}
}