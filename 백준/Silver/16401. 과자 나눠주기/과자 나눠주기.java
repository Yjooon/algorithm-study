import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] arr;

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);

		System.out.println(binarySearchMaxSnack(M, arr));
	}

	public static int binarySearchMaxSnack(int M, int[] arr) {
		int left = 1;
		int right = arr[arr.length - 1];
		int result = 0;

		while (left <= right) {
			int mid = (left + right) / 2;

			int count = 0;
			for (int snack : arr) {
				count += snack / mid;
			}

			if (count >= M) {
				result = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return result;
	}
}
