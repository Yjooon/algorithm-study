import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] dp = new int[31];
		if (N % 2 != 0) {
			System.out.println(0);
			return;
		} else {
			dp[0] = 0;
			dp[2] = 3;
			dp[4] = 11;
			for (int i = 6; i <= N; i += 2) {
				int plus = 1;
				for (int j = 0; j <= i - 4; j++) {
					plus += dp[j];
				}

				dp[i] = dp[i - 2] * dp[2] + 2 * plus;
			}
		}
		System.out.println(dp[N]);

	}
}
