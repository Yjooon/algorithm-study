import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int test_case = 1; test_case <= T; test_case++) {
			int n = Integer.parseInt(br.readLine());
			int dp1[] = new int[n];
			int dp2[] = new int[n];

			StringTokenizer st1 = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				dp1[i] = Integer.parseInt(st1.nextToken());
			}

			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				dp2[i] = Integer.parseInt(st2.nextToken());
			}
			if (n != 1) {
				dp1[1] += dp2[0];
				dp2[1] += dp1[0];
			}

			for (int i = 2; i < n; i++) {
				dp1[i] += Math.max(dp2[i - 1], dp2[i - 2]);
				dp2[i] += Math.max(dp1[i - 1], dp1[i - 2]);
			}
			System.out.println(Math.max(dp1[n - 1], dp2[n - 1]));
		}
	}
}
