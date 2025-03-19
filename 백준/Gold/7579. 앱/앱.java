
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] memoryArr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			memoryArr[i] = Integer.parseInt(st.nextToken());
		}

		int[] costArr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			costArr[i] = Integer.parseInt(st.nextToken());
		}

		int totalMemory = 0;
		for (int i = 0; i < N; i++) {
			totalMemory += memoryArr[i];
		}

		int[] dp = new int[totalMemory + 1];
		for (int i = 0; i <= totalMemory; i++) {
			dp[i] = Integer.MAX_VALUE;
		}
		dp[0] = 0;

		for (int i = 0; i < N; i++) {
			for (int j = totalMemory; j >= memoryArr[i]; j--) {
				if (dp[j - memoryArr[i]] != Integer.MAX_VALUE) {
					dp[j] = Math.min(dp[j], dp[j - memoryArr[i]] + costArr[i]);
				}
			}
		}
		int result = Integer.MAX_VALUE;
		for (int i = M; i <= totalMemory; i++) {
			result = Math.min(result, dp[i]);
		}

		System.out.println(result);
	}
}
