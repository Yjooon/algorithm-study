import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[][] baseArr;
	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		baseArr = new int[N][M];
		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				baseArr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int[][] dp = new int[N][M];
		dp[0][0] = baseArr[0][0];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (i == 0 && j == 0) {
					continue;
				} else if (i == 0) {
					dp[i][j] = dp[i][j - 1] + baseArr[i][j];
					continue;
				} else if (j == 0) {
					dp[i][j] = dp[i - 1][j] + baseArr[i][j];
					continue;
				}
				dp[i][j] = Math.max(dp[i - 1][j] + baseArr[i][j], dp[i][j - 1] + baseArr[i][j]);
			}
		}
		System.out.println(dp[N - 1][M - 1]);
	}
}