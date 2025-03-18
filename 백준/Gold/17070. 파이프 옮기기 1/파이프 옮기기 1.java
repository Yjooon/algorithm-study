
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] arr;
	static int[][][] dp;

	static int cx1;
	static int cy1;
	static int cx2;
	static int cy2;
	static int currPos; // 1: 가로 / 2: 세로 / 3: 대각선

	static int[][][] isVisited;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		dp = new int[4][N][N];
		isVisited = new int[4][N][N];
		result = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dp[1][0][1] = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 2; j < N; j++) {
				if (i == 0) {
					if (arr[i][j] != 1 && arr[i][j - 1] != 1)
						dp[1][i][j] += dp[1][i][j - 1] + dp[3][i][j - 1];
					continue;
				}

				if (arr[i][j] != 1 && arr[i][j - 1] != 1)
					dp[1][i][j] += dp[1][i][j - 1] + dp[3][i][j - 1];

				if (arr[i][j] != 1 && arr[i - 1][j] != 1)
					dp[2][i][j] += dp[2][i - 1][j] + dp[3][i - 1][j];

				if (arr[i][j] != 1 && arr[i - 1][j] != 1 && arr[i][j - 1] != 1) {
					dp[3][i][j] += dp[3][i - 1][j - 1];
					dp[3][i][j] += dp[1][i - 1][j - 1];
					dp[3][i][j] += dp[2][i - 1][j - 1];
				}

			}
		}

		System.out.println(dp[1][N - 1][N - 1] + dp[2][N - 1][N - 1] + dp[3][N - 1][N - 1]);
	}
}
