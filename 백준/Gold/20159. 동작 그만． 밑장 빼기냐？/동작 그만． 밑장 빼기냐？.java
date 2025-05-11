import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		int[] A = new int[N + 1];
		int[] B = new int[N + 1];
		int[] prefixA = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			if (i == 0) {
				prefixA[i] = arr[i];
				continue;
			}
			if (i % 2 == 0) {
				prefixA[i] = prefixA[i - 1] + arr[i];
			} else {
				prefixA[i] = prefixA[i - 1];
			}
		}

		for (int i = N - 1; i >= 0; i--) {
			if (i == N - 1 || i == N - 2) {
				if (i % 2 == 0) {
					A[i] = 0 + arr[i];
					B[i] = B[i + 1];
				} else {
					A[i] = A[i + 1];
					B[i] = 0 + arr[i];
				}
				continue;
			}
			if (i % 2 == 0) {
				A[i] = A[i + 1] + arr[i];
				B[i] = B[i + 1];
			} else {
				A[i] = A[i + 1];
				B[i] = B[i + 1] + arr[i];
			}
		}

//		for (int i : A) {
//			System.out.print(i + " ");
//		}
//		System.out.println();
//		for (int i : B) {
//			System.out.print(i + " ");
//		}
//		System.out.println();

		int result = A[0];
		for (int i = 0; i < N; i++) {
			if (i == 0) {
				result = Math.max(result, B[i]);
				continue;
			}
			if (i % 2 == 0) {
				result = Math.max(result, prefixA[i - 1] + B[i]);
			} else if (i % 2 != 0) {
				result = Math.max(result, prefixA[i] + B[i] - arr[N - 1]);
			}
		}
		System.out.println(result);

	}
}
