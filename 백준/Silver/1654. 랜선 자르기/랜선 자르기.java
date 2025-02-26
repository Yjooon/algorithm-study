import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int K;
	static long[] arr;
	static long result;

	static void calc(long left, long right) {
		if (left >= right - 1) {
			long mid = (left + right) / 2 + 1;

			int count = 0;
			for (long i : arr) {
				count += i / mid;
			}
			if (count >= K) {
				result = Math.max(result, mid);
			}
			return;
		}
		long mid = (left + right) / 2;

		int count = 0;
		for (long i : arr) {
			count += i / mid;
		}
		if (count >= K) {
			result = Math.max(result, mid);
			calc(mid, right);
		} else {
			calc(left, mid);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new long[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(arr);

		calc(0, arr[N - 1]);

		System.out.println(result);
	}
}
