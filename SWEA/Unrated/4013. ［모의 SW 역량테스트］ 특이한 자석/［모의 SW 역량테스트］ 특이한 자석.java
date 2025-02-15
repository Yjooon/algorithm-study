import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
	static int[][] magnet;
	static boolean[] isVisited;

	static void spin(int num, int dir) {
		if (isVisited[num])
			return;

		isVisited[num] = true;
		if (num < 3 && magnet[num][2] != magnet[num + 1][6]) {
			spin(num + 1, dir * -1);
		}
		if (num > 0 && magnet[num][6] != magnet[num - 1][2]) {
			spin(num - 1, dir * -1);
		}
		cw(num, dir);
	}

	// 회전
	static void cw(int num, int dir) {
		if (dir == 1) {
			int temp = magnet[num][7];
			for (int i = 7; i > 0; i--) {
				magnet[num][i] = magnet[num][i - 1];
			}
			magnet[num][0] = temp;
		} else if (dir == -1) {
			int temp = magnet[num][0];
			for (int i = 0; i < 7; i++) {
				magnet[num][i] = magnet[num][i + 1];
			}
			magnet[num][7] = temp;
		}
	}

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			int K = Integer.parseInt(br.readLine());

			magnet = new int[4][8];
			isVisited = new boolean[4];
			for (int i = 0; i < 4; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 8; j++) {
					magnet[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < K; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int magnetNum = Integer.parseInt(st.nextToken());
				int spinDir = Integer.parseInt(st.nextToken());
				Arrays.fill(isVisited, false);

				spin(magnetNum - 1, spinDir);
			}

			int result = 0;
			for (int i = 0; i < 4; i++) {
				if (magnet[i][0] == 0)
					result += 0;
				else
					result += (int) Math.pow(2, i);
			}
			System.out.println("#" + test_case + " " + result);
		}
	}
}